package com.example.pixabay.data.repository

import com.example.pixabay.domain.model.ErrorType
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

interface ExceptionHandle {

    fun handle(e: Exception): com.example.pixabay.domain.model.MyResult

    class Base : ExceptionHandle {
        override fun handle(e: Exception): com.example.pixabay.domain.model.MyResult =
            com.example.pixabay.domain.model.MyResult.Fail(
                when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.HTTP_EXCEPTION
                    is IOException -> ErrorType.IO_EXCEPTION
                    else -> ErrorType.GENERIC_ERROR
                }
            )
    }
}
