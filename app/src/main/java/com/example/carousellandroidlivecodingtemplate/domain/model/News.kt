package com.example.carousellandroidlivecodingtemplate.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val timeCreated: Long,
    val rank: Int
)
