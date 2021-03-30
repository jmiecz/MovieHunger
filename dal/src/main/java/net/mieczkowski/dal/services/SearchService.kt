package net.mieczkowski.dal.services

import net.mieczkowski.dal.BuildConfig
import net.mieczkowski.models.MovieResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
interface SearchService {

    @GET("search/movie")
    suspend fun search(
        @Query("query") searchQuery: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResults
}