package net.mieczkowski.dal.discover

import kotlinx.coroutines.runBlocking
import net.mieczkowski.models.Movie
import net.mieczkowski.models.MovieResults
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
class DiscoverServiceTest : BaseTest() {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(DiscoverService.module)
    }

    val discoverService: DiscoverService by inject()

    @Test
    fun `verifying list of movies are return`() {
        declareMock<DiscoverContract> {
            runBlocking {
                given(fetchDiscoveries(anyString())).will {
                    MovieResults(
                        listOf(
                            Movie(42, "title", null, "overview", 1337),
                            Movie(123, "another title", "someposter.jpg", "more overview", 987)
                        )
                    )
                }
            }
        }

        runBlocking {
            discoverService.discoverMovies().run {
                kotlin.test.assertEquals(2, size)

                get(0).run {
                    kotlin.test.assertEquals(42, id)
                    kotlin.test.assertEquals("title", title)
                    kotlin.test.assertNull(posterUrl)
                    kotlin.test.assertEquals("overview", overview)
                    kotlin.test.assertEquals(1337, popularity)
                }

                get(1).run {
                    kotlin.test.assertEquals(123, id)
                    kotlin.test.assertEquals("another title", title)
                    kotlin.test.assertEquals(
                        "https://image.tmdb.org/t/p/w500someposter.jpg",
                        posterUrl
                    )
                    kotlin.test.assertEquals("more overview", overview)
                    kotlin.test.assertEquals(987, popularity)
                }
            }
        }
    }
}