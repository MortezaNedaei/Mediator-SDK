package org.tapsell.admediator.main.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull

suspend fun<T> Flow<T>.isNullOrEmpty(): Boolean {
    return this
        .filterNotNull()
        .firstOrNull() === null
}