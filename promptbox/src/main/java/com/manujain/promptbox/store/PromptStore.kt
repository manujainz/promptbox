package com.manujain.promptbox.store

import com.manujain.promptbox.model.PromptEntry

/**
 * Abstracts how prompt entries are stored and retrieved.
 * Can be backed by Room, memory, files, etc.
 */
interface PromptStore {

    /**
     * Stores a new prompt-response pair with its embedding.
     */
    suspend fun put(entry: PromptEntry)

    /**
     * Returns all stored prompt entries.
     * You may optimize this later to support pagination or partial match.
     */
    suspend fun getAll(): List<PromptEntry>

    /**
     * Clears all stored entries.
     */
    suspend fun clear()
}