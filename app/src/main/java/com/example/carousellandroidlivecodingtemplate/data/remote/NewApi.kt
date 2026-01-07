package com.example.carousellandroidlivecodingtemplate.data.remote

import com.example.carousellandroidlivecodingtemplate.data.remote.dto.NewsDto
import retrofit2.http.GET

interface NewApi {

    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun getNews(): List<NewsDto>

}
