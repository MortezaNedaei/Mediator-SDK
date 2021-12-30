package org.tapsell.sdk.addmediator

import android.app.Activity
import android.content.Context
import android.util.Log
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import org.tapsell.sdk.BuildConfig

object UnityAdsManager : IUnityListener {

    override fun initialize(context: Context, unityGameId: String) {
        UnityAds.initialize(
            context,
            BuildConfig.UNITY_GAME_ID,
            object : IUnityAdsInitializationListener {
                override fun onInitializationComplete() {
                    Log.i("UnityAdsManager", "Success")
                }

                override fun onInitializationFailed(
                    error: UnityAds.UnityAdsInitializationError?,
                    message: String?
                ) {
                    Log.i("UnityAdsManager", message?.toString() ?: "Error")
                }

            })
    }

    override fun requestAd(placementId: String) {
        UnityAds.load(placementId, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String?) {
//                showAdd()
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String?,
                error: UnityAds.UnityAdsLoadError?,
                message: String?
            ) {
            }

        })
    }

    override fun showAdd(activity: Activity, placementId: String) {
        UnityAds.show(activity, placementId, object : IUnityAdsShowListener {
            override fun onUnityAdsShowFailure(
                placementId: String?,
                error: UnityAds.UnityAdsShowError?,
                message: String?
            ) {
            }

            override fun onUnityAdsShowStart(placementId: String?) {}

            override fun onUnityAdsShowClick(placementId: String?) {}

            override fun onUnityAdsShowComplete(
                placementId: String?,
                state: UnityAds.UnityAdsShowCompletionState?
            ) {
            }
        })
    }

}