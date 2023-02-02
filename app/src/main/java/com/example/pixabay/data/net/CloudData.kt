package com.example.pixabay.data.net

import com.example.pixabay.data.model.DataCloud
import javax.inject.Inject

interface CloudData {

    suspend fun fetchAnimals(): DataCloud
    suspend fun fetchFeelings(): DataCloud
    suspend fun fetchMusic(): DataCloud
    suspend fun fetchSports(): DataCloud
    suspend fun fetchIndustry(): DataCloud
    suspend fun fetchScience(): DataCloud

    class Base @Inject constructor(private val myService: MyService) : CloudData {
        override suspend fun fetchAnimals(): DataCloud = myService.fetchAnimals()
        override suspend fun fetchFeelings(): DataCloud = myService.fetchFeelings()
        override suspend fun fetchMusic(): DataCloud = myService.fetchMusic()
        override suspend fun fetchSports(): DataCloud = myService.fetchSports()
        override suspend fun fetchIndustry(): DataCloud = myService.fetchIndustry()
        override suspend fun fetchScience(): DataCloud = myService.fetchScience()
    }
}

