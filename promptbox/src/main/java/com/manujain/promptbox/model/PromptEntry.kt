package com.manujain.promptbox.model

/**
 * Represents a cached prompt and its associated metadata.
 */
data class PromptEntry(
    val prompt: String,
    val response: String,
    val embedding: FloatArray,
    val timestamp: Long = System.currentTimeMillis()
)