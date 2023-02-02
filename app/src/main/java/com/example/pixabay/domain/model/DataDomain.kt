package com.example.pixabay.domain.model

data class DataDomain(
    val feelings: List<Hit> = emptyList(),
    val animals: List<Hit> = emptyList(),
    val music: List<Hit> = emptyList(),
    val sports: List<Hit> = emptyList(),
    val industry: List<Hit> = emptyList(),
    val science: List<Hit> = emptyList()
) {
    data class Hit(
        var previewURL: String,
        var largeImageURL: String,
        var webformatURL: String
    )
}

