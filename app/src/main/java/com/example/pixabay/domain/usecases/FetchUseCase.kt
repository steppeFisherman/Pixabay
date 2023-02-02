package com.example.pixabay.domain.usecases

import com.example.pixabay.domain.Repository
import com.example.pixabay.domain.model.MyResult
import javax.inject.Inject

interface FetchUseCase {

    suspend fun fetchFeelings(): MyResult
    suspend fun fetchAnimals(): MyResult
    suspend fun fetchMusic(): MyResult
    suspend fun fetchSports(): MyResult
    suspend fun fetchIndustry(): MyResult
    suspend fun fetchScience(): MyResult

    class Base @Inject constructor(private val repository: Repository) : FetchUseCase {
        override suspend fun fetchFeelings(): MyResult = repository.fetchFeelings()
        override suspend fun fetchAnimals(): MyResult = repository.fetchAnimals()
        override suspend fun fetchMusic(): MyResult = repository.fetchMusic()
        override suspend fun fetchSports(): MyResult = repository.fetchSports()
        override suspend fun fetchIndustry(): MyResult = repository.fetchIndustry()
        override suspend fun fetchScience(): MyResult = repository.fetchScience()
    }
}
