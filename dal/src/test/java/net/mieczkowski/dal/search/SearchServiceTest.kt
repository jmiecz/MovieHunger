package net.mieczkowski.dal.search

import kotlinx.coroutines.runBlocking
import net.mieczkowski.models.Movie
import net.mieczkowski.models.MovieResults
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by Josh Mieczkowski on 3/31/2021.
 */
class SearchServiceTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { Mockito.mock(it.java) }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(SearchService.module)
    }

    val searchService: SearchService by inject()

    @Test
    fun `verifying list of movies are return`() {
        declareMock<SearchContract> {
            runBlocking {
                given(search(anyString(), anyString())).will {
                    MovieResults(listOf(
                        Movie(42, "title", null, "overview", 1337),
                        Movie(123, "another title", "someposter.jpg", "more overview", 987)
                    ))
                }
            }
        }

        runBlocking {
            searchService.search("").run {
                assertEquals(2, size)

                get(0).run {
                    assertEquals(42, id)
                    assertEquals("title", title)
                    assertNull(posterUrl)
                    assertEquals("overview", overview)
                    assertEquals(1337, popularity)
                }

                get(1).run {
                    assertEquals(123, id)
                    assertEquals("another title", title)
                    assertEquals("https://image.tmdb.org/t/p/w500someposter.jpg", posterUrl)
                    assertEquals("more overview", overview)
                    assertEquals(987, popularity)
                }
            }
        }
    }
}