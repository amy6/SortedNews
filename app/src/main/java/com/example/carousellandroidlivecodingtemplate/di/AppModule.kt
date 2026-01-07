package com.example.carousellandroidlivecodingtemplate.di

import com.example.carousellandroidlivecodingtemplate.data.remote.NewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewApi {
        return Retrofit.Builder()
            .baseUrl("https://storage.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewApi::class.java)
    }
}
