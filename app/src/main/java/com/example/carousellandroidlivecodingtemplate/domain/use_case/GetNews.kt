package com.example.carousellandroidlivecodingtemplate.domain.use_case

import com.example.carousellandroidlivecodingtemplate.domain.model.News
import com.example.carousellandroidlivecodingtemplate.domain.repository.NewsRepository
import com.example.carousellandroidlivecodingtemplate.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNews @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.loadNews()
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
