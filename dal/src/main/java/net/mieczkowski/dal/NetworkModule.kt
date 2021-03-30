package net.mieczkowski.dal

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
internal object NetworkModule {

    val module = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
    }

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .header("Authorization", "Bearer ${BuildConfig.TMDB_API_KEY}")
                    .build()

                it.proceed(request)
            }
            .build()

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/")
            .build()
}