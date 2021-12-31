package org.tapsell.sdk.addmediator

import android.app.Activity
import android.content.Context

/**
 * defines library accessible methods
 */
interface IAdMediator {
    fun initialize(context: Context)
    fun requestAd(activity: Activity, onResult: (Boolean) -> Unit)
    fun showAdd(activity: Activity)
}