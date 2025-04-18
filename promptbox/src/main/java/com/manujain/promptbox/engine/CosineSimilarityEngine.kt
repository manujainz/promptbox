package com.manujain.promptbox.engine

import kotlin.math.sqrt

/**
 * Default implementation using cosine similarity.
 */
class CosineSimilarityEngine : SimilarityEngine {

    override fun similarity(vec1: FloatArray, vec2: FloatArray): Float {
        require(vec1.size == vec2.size) { "Vectors must be of the same size" }

        var dot = 0.0f
        var mag1 = 0.0f
        var mag2 = 0.0f

        for (i in vec1.indices) {
            dot += vec1[i] * vec2[i]
            mag1 += vec1[i] * vec1[i]
            mag2 += vec2[i] * vec2[i]
        }

        val denominator = sqrt(mag1) * sqrt(mag2)
        return if (denominator == 0f) 0f else dot / denominator
    }
}