package com.example.pixabay.domain.model

sealed class MyResult {
    data class Success(val itemsDomain: DataDomain) : MyResult()
    data class Fail(val errorType: ErrorType) : MyResult()
}
