package net.mieczkowski.dal.exts

import net.mieczkowski.dal.BuildConfig
import net.mieczkowski.models.Movie

/**
 * Created by Josh Mieczkowski on 4/1/2021.
 */
internal fun Movie.setPosterUrl() {
    if (posterUrl != null) posterUrl = BuildConfig.TMDB_IMG_URL + posterUrl
}