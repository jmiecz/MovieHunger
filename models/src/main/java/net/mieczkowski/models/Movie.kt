package net.mieczkowski.models

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val overview: String,
    val popularity: Number
)