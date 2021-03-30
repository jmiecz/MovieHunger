package net.mieczkowski.dal

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.mieczkowski.dal.services.SearchService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
internal object NetworkModule {

    val module = module {
        single { provideObjectMapper() }
        single { provideJacksonFactory(get()) }

        single { provideOkHttpClient() }
        single { provideRetrofit(get(), get()) }

        single { get<Retrofit>().create(SearchService::class.java) }
    }

    private fun provideObjectMapper(): ObjectMapper = jacksonObjectMapper().apply {
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    private fun provideJacksonFactory(objectMapper: ObjectMapper): Converter.Factory =
        JacksonConverterFactory.create(
            objectMapper
        )

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

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(BuildConfig.TMDB_URL + "/")
            .addConverterFactory(jsonFactory)
            .build()
}