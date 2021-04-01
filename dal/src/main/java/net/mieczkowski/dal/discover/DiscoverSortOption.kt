package net.mieczkowski.dal.discover

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
enum class DiscoverSortOption(internal val value: String) {
    PopularAsc("popularity.asc"),
    PopularDesc("popularity.desc"),
    ReleaseDateAsc("release_date.asc"),
    ReleaseDateDesc("release_date.desc"),
    RatingAsc("vote_average.asc"),
    RatingDesc("vote_average.desc")
}