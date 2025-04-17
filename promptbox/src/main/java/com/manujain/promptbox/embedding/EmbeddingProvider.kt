package com.manujain.promptbox.embedding

/**
 * Defines how prompt embeddings are generated.
 * Can be implemented using on-device models (TFLite, ONNX) or remote APIs.
 */
interface EmbeddingProvider {

    /**
     * Generates a float array embedding for the given input string.
     *
     * @param text The input prompt or query.
     * @return A FloatArray representing the semantic embedding of the text.
     */
    suspend fun embed(text: String): FloatArray
}