package com.aanastasia.houlakchallenge.data.util

sealed class ApiFailure : Throwable() {

    data class Service(val errorCode: Int, val errorMessage: String?) : ApiFailure()
    object Unknown : ApiFailure()
    object Network : ApiFailure()

}