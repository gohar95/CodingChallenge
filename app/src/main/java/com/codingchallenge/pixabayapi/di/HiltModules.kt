package com.codingchallenge.pixabayapi.di

import com.codingchallenge.pixabayapi.network.ApiService
import com.codingchallenge.pixabayapi.ui.components.MainRepository
import com.codingchallenge.pixabayapi.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideMainRepository(apiService: ApiService):MainRepository{
        return MainRepository(apiService = apiService)
    }


}