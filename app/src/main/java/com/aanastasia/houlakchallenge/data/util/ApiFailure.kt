package com.aanastasia.houlakchallenge.data.util

sealed class ApiFailure : LayerFailure.DataFailure() {
    data class ServiceUnavailableError(val serverMessage: String? = null) : ApiFailure()
    object JsonParseError : ApiFailure()
    object ExceptionError : ApiFailure()
}

fun ApiFailure.toDataFailure(): LayerFailure.DataFailure {
    return when (this) {
        is ApiFailure.ServiceUnavailableError -> DataFailure.ServiceUnavailableError
        else -> DataFailure.Unknown
    }
}