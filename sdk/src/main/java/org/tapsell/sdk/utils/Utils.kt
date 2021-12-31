package org.tapsell.sdk.utils

object Utils {

    fun isWaterfallExpired(epoch: Long): Boolean =
        System.currentTimeMillis() - epoch >= Constants.EPOCH_HOUR_MILLISECONDS
}