package net.mieczkowski.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
data class Movie(
    @JsonProperty("id") val id: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("poster_path") val posterUrl: String?,
    @JsonProperty("overview") val overview: String,
    @JsonProperty("popularity") val popularity: Number
)