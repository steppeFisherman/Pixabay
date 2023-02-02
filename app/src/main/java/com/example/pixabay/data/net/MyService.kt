package com.example.pixabay.data.net

import com.example.pixabay.data.model.DataCloud
import retrofit2.http.GET

interface MyService {

    companion object {
        const val BASE_URL = "https://pixabay.com/api/"
        private const val KEY = "?key=33106230-b104905cd7ff74ed17e2229af"
        private const val PER_PAGE = "&per_page=10"
    }

    @GET("$KEY&category=animals&image_type=photo$PER_PAGE")
    suspend fun fetchAnimals(): DataCloud

    @GET("$KEY&category=feelings&image_type=photo$PER_PAGE")
    suspend fun fetchFeelings(): DataCloud

    @GET("$KEY&category=music&image_type=photo$PER_PAGE")
    suspend fun fetchMusic(): DataCloud

    @GET("$KEY&category=sports&image_type=photo$PER_PAGE")
    suspend fun fetchSports(): DataCloud

    @GET("$KEY&category=industry&image_type=photo$PER_PAGE")
    suspend fun fetchIndustry(): DataCloud

    @GET("$KEY&category=science&image_type=photo$PER_PAGE")
    suspend fun fetchScience(): DataCloud
}
