package com.aanastasia.houlakchallenge.data.util

sealed class DataFailure : LayerFailure.DataFailure() {
    object ServiceUnavailableError : DataFailure()

    object Unknown : DataFailure()
}