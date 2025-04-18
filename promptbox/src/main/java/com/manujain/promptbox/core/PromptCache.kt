package com.manujain.promptbox.core

import com.manujain.promptbox.embedding.EmbeddingProvider
import com.manujain.promptbox.engine.SimilarityEngine
import com.manujain.promptbox.model.PromptEntry
import com.manujain.promptbox.store.PromptStore

/**
 * Internal implementation class backing PromptBox.
 */
class PromptCache(
    private val embeddingProvider: EmbeddingProvider,
    private val promptStore: PromptStore,
    private val similarityEngine: SimilarityEngine,
    private val similarityThreshold: Float = 0.85f
) {

    /**
     * Returns a cached response if a similar prompt is found.
     * Otherwise, fetches a new response using [fetch], stores it, and returns it.
     */
    suspend fun getOrFetch(
        prompt: String,
        fetch: suspend (String) -> String
    ): String {
        val newEmbedding = embeddingProvider.embed(prompt)
        val entries = promptStore.getAll()

        val bestMatch = entries.maxByOrNull {
            similarityEngine.similarity(newEmbedding, it.embedding)
        }

        val bestScore = bestMatch?.let { similarityEngine.similarity(newEmbedding, it.embedding) } ?: 0f

        if (bestMatch != null && bestScore >= similarityThreshold) {
            return bestMatch.response
        }

        val response = fetch(prompt)
        val newEntry = PromptEntry(prompt, response, newEmbedding)
        promptStore.put(newEntry)
        return response
    }

    suspend fun clear() {
        promptStore.clear()
    }

    suspend fun put(prompt: String, response: String) {
        val embedding = embeddingProvider.embed(prompt)
        promptStore.put(PromptEntry(prompt, response, embedding))
    }

    suspend fun hasSimilar(prompt: String): Boolean {
        val embedding = embeddingProvider.embed(prompt)
        return promptStore.getAll().any {
            similarityEngine.similarity(embedding, it.embedding) >= similarityThreshold
        }
    }
}