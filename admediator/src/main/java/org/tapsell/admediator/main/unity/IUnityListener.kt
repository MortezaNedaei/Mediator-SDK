package org.tapsell.admediator.main.unity

import android.app.Activity
import android.content.Context
import org.tapsell.admediator.BuildConfig
import org.tapsell.admediator.main.IAdRequestListener
import org.tapsell.admediator.main.IAdShowListener

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