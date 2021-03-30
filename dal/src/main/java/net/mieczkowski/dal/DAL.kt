package net.mieczkowski.dal

import net.mieczkowski.dal.modules.NetworkModule
import net.mieczkowski.dal.modules.ParsingModule
import net.mieczkowski.dal.search.SearchService
import org.koin.core.context.loadKoinModules

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
object DAL {

    fun init() {
        loadKoinModules(
            listOf(
                ParsingModule.module,
                NetworkModule.module,

                SearchService.module
            )
        )
    }
}