package com.manujain.promptbox.engine

/**
 * Interface to compute similarity between two float vectors.
 */
interface SimilarityEngine {
    fun similarity(vec1: FloatArray, vec2: FloatArray): Float
}