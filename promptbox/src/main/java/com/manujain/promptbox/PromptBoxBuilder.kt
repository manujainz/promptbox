package com.manujain.promptbox

import android.content.Context
import com.manujain.promptbox.core.PromptCache
import com.manujain.promptbox.embedding.EmbeddingProvider
import com.manujain.promptbox.engine.CosineSimilarityEngine
import com.manujain.promptbox.engine.SimilarityEngine
import com.manujain.promptbox.internal.PromptBoxImpl
import com.manujain.promptbox.store.InMemoryPromptStore
import com.manujain.promptbox.store.PromptStore

/**
 * A builder to configure and create a PromptBox instance.
 */
class PromptBoxBuilder(private val context: Context) {

    private var embeddingProvider: EmbeddingProvider? = null
    private var promptStore: PromptStore? = null
    private var similarityEngine: SimilarityEngine = CosineSimilarityEngine()
    private var similarityThreshold: Float = 0.85f

    fun setEmbeddingProvider(provider: EmbeddingProvider): PromptBoxBuilder {
        this.embeddingProvider = provider
        return this
    }

    fun setPromptStore(store: PromptStore): PromptBoxBuilder {
        this.promptStore = store
        return this
    }

    fun setSimilarityEngine(engine: SimilarityEngine): PromptBoxBuilder {
        this.similarityEngine = engine
        return this
    }

    fun setSimilarityThreshold(threshold: Float): PromptBoxBuilder {
        this.similarityThreshold = threshold
        return this
    }

    fun build(): PromptBox {
        val provider = embeddingProvider
            ?: throw IllegalStateException("EmbeddingProvider must be set")
        val store = promptStore ?: InMemoryPromptStore()

        val cache = PromptCache(
            embeddingProvider = provider,
            promptStore = store,
            similarityEngine = similarityEngine,
            similarityThreshold = similarityThreshold
        )

        return PromptBoxImpl(cache)
    }
}