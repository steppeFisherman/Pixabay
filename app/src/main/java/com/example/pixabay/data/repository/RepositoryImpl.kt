package com.example.pixabay.data.repository

import com.example.pixabay.domain.Repository
import com.example.pixabay.domain.model.MyResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cloudSource: CloudSource,
    private val exceptionHandle: ExceptionHandle
) : Repository {

    override suspend fun fetchFeelings(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchFeelings())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }

    override suspend fun fetchAnimals(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchAnimals())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }

    override suspend fun fetchMusic(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchMusic())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }

    override suspend fun fetchSports(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchSports())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }

    override suspend fun fetchIndustry(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchIndustry())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }

    override suspend fun fetchScience(): MyResult =
        try {
            MyResult.Success(cloudSource.fetchScience())
        } catch (e: Exception) {
            exceptionHandle.handle(e)
        }
}





