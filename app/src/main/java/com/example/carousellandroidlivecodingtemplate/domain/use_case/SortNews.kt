package com.example.carousellandroidlivecodingtemplate.domain.use_case

import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.domain.repository.NewsRepository
import com.example.carousellandroidlivecodingtemplate.presentation.SortType
import javax.inject.Inject

class SortNews @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(news: List<News>, sortType: SortType): List<News> {
        return repository.sortNews(news, sortType)
    }
}
