package com.example.carousellandroidlivecodingtemplate.data.repository

import com.example.carousellandroidlivecodingtemplate.data.remote.NewApi
import com.example.carousellandroidlivecodingtemplate.data.remote.dto.NewsDto
import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.domain.repository.NewsRepository
import com.example.carousellandroidlivecodingtemplate.presentation.SortType
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewApi
) : NewsRepository {

    override suspend fun loadNews(): List<News> {
        return api.getNews().map { it.toNews() }
    }

    override fun sortNews(news: List<News>, sortType: SortType): List<News> {
        return when (sortType) {
            SortType.RECENT -> news.sortedByDescending { it.timeCreated }
            SortType.POPULAR -> news.sortedBy { it.rank }
        }
    }
}

fun NewsDto.toNews(): News {
    return News(
        id = id,
        title = title,
        description = description,
        bannerUrl = bannerUrl,
        timeCreated = timeCreated,
        rank = rank
    )
}
