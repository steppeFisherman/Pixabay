package com.example.pixabay.data.repository

import com.example.pixabay.data.model.DataCloud
import com.example.pixabay.domain.model.DataDomain

interface MapCloudToDomain {

    fun transformHit(dataCloud: DataCloud.Hit): DataDomain.Hit
    fun transform(dataCloud: DataCloud): List<DataDomain.Hit>

    class Base : MapCloudToDomain {
        override fun transformHit(dataCloud: DataCloud.Hit): DataDomain.Hit =
            DataDomain.Hit(
                previewURL = dataCloud.previewURL,
                largeImageURL = dataCloud.largeImageURL,
                webformatURL = dataCloud.webformatURL
            )

        override fun transform(dataCloud: DataCloud): List<DataDomain.Hit> =
            listCloudToDomain(dataCloud.hits)

        private fun listCloudToDomain(list: List<DataCloud.Hit>)
                : List<DataDomain.Hit> = list.map { transformHit(it) }
    }
}
