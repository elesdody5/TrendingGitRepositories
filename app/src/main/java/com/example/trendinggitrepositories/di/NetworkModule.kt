package com.example.trendinggitrepositories.di

import com.example.trendinggitrepositories.data.remote.network.BASE_URL
import com.example.trendinggitrepositories.data.remote.network.RemoteRepositoryApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Makes Hilt provide Retrofit instance when a Retrofit type is requested
    @Provides
    @Singleton
    fun providesRetrofit(): RemoteRepositoryApiService {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(RemoteRepositoryApiService::class.java)
    }
}