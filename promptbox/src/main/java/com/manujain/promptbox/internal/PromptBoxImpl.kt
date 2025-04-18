package com.manujain.promptbox.internal

import com.manujain.promptbox.PromptBox
import com.manujain.promptbox.core.PromptCache

/**
 * Internal implementation of the PromptBox interface,
 * delegating to the PromptCache engine.
 */
class PromptBoxImpl(
    private val cache: PromptCache
) : PromptBox {

    override suspend fun getOrFetch(
        prompt: String,
        fetch: suspend (String) -> String
    ): String = cache.getOrFetch(prompt, fetch)

    override suspend fun clear() {
        cache.clear()
    }

    override suspend fun put(prompt: String, response: String) {
        cache.put(prompt, response)
    }

    override suspend fun hasSimilar(prompt: String): Boolean {
        return cache.hasSimilar(prompt)
    }
}