package net.mieczkowski.dal.discover

import net.mieczkowski.dal.exts.setPosterUrl
import net.mieczkowski.models.Movie
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
class DiscoverService : KoinComponent {
    internal companion object {
        val module = module {
            single { get<Retrofit>().create(DiscoverContract::class.java) }
            factory { DiscoverService() }
        }
    }

    private val discoverContract: DiscoverContract by inject()

    suspend fun discoverMovies(sortByOption: DiscoverSortOption = DiscoverSortOption.PopularDesc): List<Movie> =
        discoverContract.fetchDiscoveries(sortByOption.value).movies.onEach { it.setPosterUrl() }
}