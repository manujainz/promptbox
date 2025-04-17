package com.manujain.promptbox.store

import com.manujain.promptbox.model.PromptEntry
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A simple in-memory implementation of PromptStore.
 */
class InMemoryPromptStore : PromptStore {

    private val cache = mutableListOf<PromptEntry>()
    private val mutex = Mutex() // to make it thread-safe

    override suspend fun put(entry: PromptEntry) {
        mutex.withLock {
            cache.add(entry)
        }
    }

    override suspend fun getAll(): List<PromptEntry> {
        return mutex.withLock { cache.toList() }
    }

    override suspend fun clear() {
        mutex.withLock { cache.clear() }
    }
}