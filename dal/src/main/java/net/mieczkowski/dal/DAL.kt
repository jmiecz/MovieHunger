package net.mieczkowski.dal

import org.koin.core.context.loadKoinModules

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
object DAL {

    fun init(){
        loadKoinModules(listOf(
            NetworkModule.module
        ))
    }
}