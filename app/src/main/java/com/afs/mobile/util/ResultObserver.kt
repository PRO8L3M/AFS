package com.afs.mobile.util

import androidx.lifecycle.Observer
import com.afs.mobile.data.entity.Result

class ResultObserver<T : Any>(
    private val onSuccess: (T) -> Unit,
    private val onFailure: (Exception) -> Unit,
    private val onLoading: () -> Unit
) : Observer<Result<T>> {

    override fun onChanged(result: Result<T>) {
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Failure -> onFailure(result.exception)
            is Result.Loading -> onLoading()
        }
    }
}