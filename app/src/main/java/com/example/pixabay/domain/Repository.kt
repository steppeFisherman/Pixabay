package com.example.pixabay.domain

import com.example.pixabay.domain.model.MyResult

interface Repository {
    suspend fun fetchFeelings(): MyResult
    suspend fun fetchAnimals(): MyResult
    suspend fun fetchMusic(): MyResult
    suspend fun fetchSports(): MyResult
    suspend fun fetchIndustry(): MyResult
    suspend fun fetchScience(): MyResult
}