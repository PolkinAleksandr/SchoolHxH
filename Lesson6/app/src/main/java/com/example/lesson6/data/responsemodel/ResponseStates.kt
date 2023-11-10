package com.example.lesson6.data.responsemodel

sealed class ResponseStates<T>() {
    class Loading<T>() : ResponseStates<T>()

    class Success<T>(val data: T) : ResponseStates<T>()

    class Failure<T>(val e: Exception) : ResponseStates<T>()
}