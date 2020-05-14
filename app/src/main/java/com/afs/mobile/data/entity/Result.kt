package com.afs.mobile.data.entity

import java.lang.Exception

sealed class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
}

inline fun <T> safeCall(call: (() -> T)): Result<T> =
    try {
        Result.Success(call())
    } catch (exception: Exception) {
        Result.Failure(exception)
    }

inline infix fun <T> Result<T>.doOnSuccess(f: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        f(data)
    }
    return this
}

fun <T> Result<T>.onSuccess(): T? {
    return if (this is Result.Success) data else null
}


