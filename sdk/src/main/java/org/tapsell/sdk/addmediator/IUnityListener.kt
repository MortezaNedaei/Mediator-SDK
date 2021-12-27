package org.tapsell.sdk.addmediator

import android.app.Activity
import android.content.Context
import ir.tapsell.plus.TapsellPlusInitListener
import org.tapsell.sdk.BuildConfig

interface IUnityListener {
    fun initialize(context: Context, unityGameId: String = BuildConfig.UNITY_GAME_ID)
    fun requestAd(placementId: String = "video")
    fun showAdd(activity: Activity, placementId: String = "video")
}