package net.mieczkowski.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
data class MovieResults(
    @JsonProperty("results") val movies: List<Movie>
)