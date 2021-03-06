package net.mieczkowski.dal.search

import net.mieczkowski.dal.exts.setPosterUrl
import net.mieczkowski.models.Movie
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
class SearchService : KoinComponent {

    internal companion object {
        val module = module {
            single { get<Retrofit>().create(SearchContract::class.java) }
            factory { SearchService() }
        }
    }

    private val searchContract: SearchContract by inject()

    suspend fun search(query: String): List<Movie> =
        searchContract.search(query).movies.onEach { it.setPosterUrl() }
}