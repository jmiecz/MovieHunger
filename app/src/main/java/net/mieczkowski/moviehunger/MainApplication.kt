package net.mieczkowski.moviehunger

import android.app.Application
import net.mieczkowski.dal.DAL
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
        }

        DAL.init()
    }
}