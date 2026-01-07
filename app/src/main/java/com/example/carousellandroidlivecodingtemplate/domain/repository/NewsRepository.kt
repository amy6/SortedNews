package com.example.carousellandroidlivecodingtemplate.domain.repository

import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.presentation.SortType

interface NewsRepository {
    suspend fun loadNews(): List<News>
    fun sortNews(news: List<News>, sortType: SortType): List<News>
    // fun loadAds(): List<Ad>

}
