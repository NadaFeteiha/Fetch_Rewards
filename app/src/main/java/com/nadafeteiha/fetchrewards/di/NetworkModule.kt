package com.nadafeteiha.fetchrewards.di

import com.google.gson.Gson
import com.nadafeteiha.fetchrewards.BuildConfig
import com.nadafeteiha.fetchrewards.data.remote.service.RewardsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    val gson = Gson()
    single { gson }

    val gsonConverterFactory = GsonConverterFactory.create(gson)
    single { gsonConverterFactory }

    val okHttpClient = OkHttpClient.Builder().build()
    single { okHttpClient }

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
    single { retrofit }

    single { provideRewardsService(retrofit) }
}

fun provideRewardsService(retrofit: Retrofit): RewardsService {
    return retrofit.create(RewardsService::class.java)
}

