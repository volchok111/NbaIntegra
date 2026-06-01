package com.metra.di

import com.metra.data.remote.NbaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.balldontlie.io/v1/"

val networkModule =
    module {
        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single {
            OkHttpClient
                .Builder()
                .addInterceptor { chain ->
                    val apiKey: String = get(named("apiKey"))
                    val request =
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", apiKey)
                            .build()

                    chain.proceed(request)
                }.addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        single {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<NbaApi> {
            get<Retrofit>().create(NbaApi::class.java)
        }
    }
