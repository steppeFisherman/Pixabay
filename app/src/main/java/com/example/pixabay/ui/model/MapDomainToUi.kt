package com.example.pixabay.ui.model

import com.example.pixabay.domain.model.DataDomain


interface MapDomainToUi {

    fun transformHit(dataDomain: DataDomain.Hit): DataUi.Hit
    fun transform(dataDomain: DataDomain): DataUi

    class Base : MapDomainToUi {
        override fun transformHit(dataDomain: DataDomain.Hit): DataUi.Hit =
            DataUi.Hit(
                previewURL = dataDomain.previewURL,
                largeImageURL = dataDomain.largeImageURL,
                webformatURL = dataDomain.webformatURL
            )

        override fun transform(dataDomain: DataDomain): DataUi =
            DataUi(
                feelings = listDomainToUi(dataDomain.feelings),
                animals = listDomainToUi(dataDomain.animals),
                music = listDomainToUi(dataDomain.music),
                sports = listDomainToUi(dataDomain.sports),
                industry = listDomainToUi(dataDomain.industry),
                science = listDomainToUi(dataDomain.science),
            )

        private fun listDomainToUi(list: List<DataDomain.Hit>): List<DataUi.Hit> =
            list.map { transformHit(it) }
    }
}