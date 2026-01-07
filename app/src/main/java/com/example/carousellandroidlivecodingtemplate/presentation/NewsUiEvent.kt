package com.example.carousellandroidlivecodingtemplate.presentation

sealed interface NewsUiEvent {
    data class Sort(val sortType: SortType) : NewsUiEvent
}
