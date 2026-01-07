package com.example.carousellandroidlivecodingtemplate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.domain.use_case.GetNews
import com.example.carousellandroidlivecodingtemplate.domain.use_case.SortNews
import com.example.carousellandroidlivecodingtemplate.domain.util.Resource
import com.example.carousellandroidlivecodingtemplate.presentation.NewsUiEvent
import com.example.carousellandroidlivecodingtemplate.presentation.NewsUiState
import com.example.carousellandroidlivecodingtemplate.presentation.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews,
    private val sortNews: SortNews
) : ViewModel() {

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var newsList: List<News> = emptyList()

    init {
        loadNews()
    }

    fun onEvent(event: NewsUiEvent) {
        when (event) {
            is NewsUiEvent.Sort -> doSortNews(event.sortType)
        }
    }

    private fun loadNews() {
        getNews().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    newsList = result.data ?: emptyList()
                    if (newsList.isEmpty()) {
                        _uiState.value = NewsUiState.Empty
                    } else {
                        doSortNews(SortType.RECENT) // Default sort
                    }
                }

                is Resource.Error -> {
                    _uiState.value =
                        NewsUiState.Error(result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _uiState.value = NewsUiState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun doSortNews(sortType: SortType) {
        val sortedNews = sortNews(newsList, sortType)
        _uiState.value = NewsUiState.Success(sortedNews)
    }
}
