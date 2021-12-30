package org.tapsell.sdk.addmediator.unity

import android.app.Activity
import android.content.Context
import org.tapsell.sdk.BuildConfig
import org.tapsell.sdk.addmediator.IAdRequestListener
import org.tapsell.sdk.addmediator.IAdShowListener

interface IUnityListener {

    fun initialize(
        context: Context,
        unityGameId: String = BuildConfig.UNITY_GAME_ID
    )

    fun requestAd(
        placementId: String = "video",
        adRequestListener: IAdRequestListener,
    )

    fun showAdd(
        activity: Activity,
        placementId: String = "video",
        adShowListener: IAdShowListener,
    )
}