package com.example.pixabay.data.repository

import kotlinx.coroutines.*

interface ToDispatch {

    fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun launchIO(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun<T> async(scope: CoroutineScope, block: suspend CoroutineScope.() -> T): Deferred<T>

    class Base : ToDispatch {
        override fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit) =
            scope.launch(Dispatchers.Main, block = block)

        override fun launchIO(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit) =
            scope.launch(Dispatchers.IO, block = block)

        override fun <T> async(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> T
        ): Deferred<T> = scope.async(Dispatchers.IO, block = block)
    }
}