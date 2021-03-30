package net.mieczkowski.dal.modules

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by Josh Mieczkowski on 3/30/2021.
 */
internal object ParsingModule {

    val module = module {
        single { provideObjectMapper() }
        single { provideJacksonFactory(get()) }
    }

    private fun provideObjectMapper(): ObjectMapper = jacksonObjectMapper().apply {
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    private fun provideJacksonFactory(objectMapper: ObjectMapper): Converter.Factory =
        JacksonConverterFactory.create(
            objectMapper
        )
}