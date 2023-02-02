package com.example.pixabay.data.repository

import com.example.pixabay.data.net.CloudData
import com.example.pixabay.domain.model.DataDomain
import javax.inject.Inject

interface CloudSource {

    suspend fun fetchFeelings(): DataDomain
    suspend fun fetchAnimals(): DataDomain
    suspend fun fetchMusic(): DataDomain
    suspend fun fetchSports(): DataDomain
    suspend fun fetchIndustry(): DataDomain
    suspend fun fetchScience(): DataDomain

    class Base @Inject constructor(
        private val mapperCloudToDomain: MapCloudToDomain,
        private val cloudData: CloudData,
        private val dispatchers: ToDispatch,
    ) : CloudSource {

        override suspend fun fetchFeelings(): DataDomain {
            val cloud = cloudData.fetchFeelings()
            return DataDomain(feelings = mapperCloudToDomain.transform(cloud))
        }

        override suspend fun fetchAnimals(): DataDomain {
            val cloud = cloudData.fetchAnimals()
            return DataDomain(animals = mapperCloudToDomain.transform(cloud))
        }

        override suspend fun fetchMusic(): DataDomain {
            val cloud = cloudData.fetchMusic()
            return DataDomain(music = mapperCloudToDomain.transform(cloud))
        }

        override suspend fun fetchSports(): DataDomain {
            val cloud = cloudData.fetchSports()
            return DataDomain(sports = mapperCloudToDomain.transform(cloud))
        }

        override suspend fun fetchIndustry(): DataDomain {
            val cloud = cloudData.fetchIndustry()
            return DataDomain(industry = mapperCloudToDomain.transform(cloud))
        }

        override suspend fun fetchScience(): DataDomain {
            val cloud = cloudData.fetchScience()
            return DataDomain(science = mapperCloudToDomain.transform(cloud))
        }
    }
}
