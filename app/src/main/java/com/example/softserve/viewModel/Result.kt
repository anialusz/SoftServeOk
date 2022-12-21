package com.example.softserve.viewModel

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<out R>(val value: R) : ResultState<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ) : ResultState<Nothing>()
}
