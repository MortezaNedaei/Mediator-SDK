package org.tapsell.sdk.addmediator.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import org.tapsell.sdk.data.local.db.waterfall.WaterfallEntity

suspend fun<T> Flow<T>.isNullOrEmpty(): Boolean {
    return this
        .filterNotNull()
        .firstOrNull() === null
}