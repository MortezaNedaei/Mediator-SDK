package org.tapsell.sdk.addmediator

import android.app.Activity
import android.content.Context

interface IAdMediator {
    fun initialize(context: Context)
    fun requestAd(activity: Activity)
    fun showAdd(activity: Activity)
}