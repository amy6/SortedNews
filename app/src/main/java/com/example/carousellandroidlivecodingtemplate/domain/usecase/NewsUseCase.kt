package com.example.carousellandroidlivecodingtemplate.domain.usecase

import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.domain.repository.NewsRepository

class NewsUseCase(val repository: NewsRepository) {
    suspend fun loadNews(): List<News> {
        return repository.loadNews()
    }
}