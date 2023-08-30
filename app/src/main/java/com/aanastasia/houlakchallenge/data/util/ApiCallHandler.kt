package com.aanastasia.houlakchallenge.data.util

import retrofit2.Response

interface ApiCallHandler {

    suspend fun <T> process(apiCall: suspend () -> Response<T>): Result<T>

}