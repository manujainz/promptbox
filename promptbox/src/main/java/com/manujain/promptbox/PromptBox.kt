package com.manujain.promptbox

interface PromptBox {

    /**
     * Returns a cached or freshly fetched response for a given prompt.
     * Uses semantic similarity to check for existing matches.
     */
    suspend fun getOrFetch(
        prompt: String,
        fetch: suspend (String) -> String
    ): String

    /**
     * Clears all stored prompt-response entries.
     */
    suspend fun clear()

    /**
     * Forcefully caches a prompt-response pair, overriding similarity matching.
     */
    suspend fun put(prompt: String, response: String)

    /**
     * Optional: checks if the cache already contains a semantically similar prompt.
     */
    suspend fun hasSimilar(prompt: String): Boolean
}