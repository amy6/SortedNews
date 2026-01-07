package com.example.carousellandroidlivecodingtemplate.presentation

import com.example.carousellandroidlivecodingtemplate.domain.model.News

sealed interface NewsUiState {
    object Loading : NewsUiState
    data class Success(val news: List<News>) : NewsUiState
    data class Error(val message: String) : NewsUiState
    object Empty : NewsUiState
}
