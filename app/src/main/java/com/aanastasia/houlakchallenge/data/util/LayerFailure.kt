package com.aanastasia.houlakchallenge.data.util

sealed class LayerFailure : Throwable() {
    abstract class DataFailure : LayerFailure()
    abstract class FeatureFailure : LayerFailure()
}