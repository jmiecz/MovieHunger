package net.mieczkowski.dal.discover

import net.mieczkowski.models.MovieResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
interface DiscoverContract {

    @GET("discover/movie")
    suspend fun fetchDiscoveries(@Query("sort_by") sortBy: String): MovieResults
}