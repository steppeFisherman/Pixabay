package com.example.pixabay.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUi(
    val feelings: List<Hit>,
    val animals: List<Hit>,
    val music: List<Hit>,
    val sports: List<Hit>,
    val industry: List<Hit>,
    val science: List<Hit>
) : Parcelable {

    @Parcelize
    data class Hit(
        var previewURL: String,
        var largeImageURL: String,
        var webformatURL: String
    ) : Parcelable
}