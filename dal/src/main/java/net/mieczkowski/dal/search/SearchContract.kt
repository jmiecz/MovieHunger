package net.mieczkowski.dal.search

import net.mieczkowski.dal.BuildConfig
import net.mieczkowski.models.MovieResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
interface SearchContract {

    @GET("search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResults
}