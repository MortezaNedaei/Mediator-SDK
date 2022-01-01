package org.tapsell.admediator.main.unity

import android.app.Activity
import android.content.Context
import android.util.Log
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import org.tapsell.admediator.BuildConfig
import org.tapsell.admediator.main.IAdRequestListener
import org.tapsell.admediator.main.IAdShowListener
import org.tapsell.admediator.data.model.enums.AdType
import org.tapsell.admediator.data.model.response.AdRequestResponse

object UnityAdsManager : IUnityListener {

    const val TAG = "UnityAdsManager"

    override fun initialize(context: Context, unityGameId: String) {
        UnityAds.initialize(
            context,
            BuildConfig.UNITY_GAME_ID,
            object : IUnityAdsInitializationListener {
                override fun onInitializationComplete() {
                    Log.i(TAG, "Success")
                }

                override fun onInitializationFailed(
                    error: UnityAds.UnityAdsInitializationError?,
                    message: String?
                ) {
                    Log.i("$TAG Error:", message?.toString() ?: "")
                }

            })
    }

    override fun requestAd(placementId: String, adRequestListener: IAdRequestListener) {
        UnityAds.load(placementId, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String?) {
                adRequestListener.onSuccess(
                    AdRequestResponse(
                        id = placementId,
                        adType = AdType.UNITY,
                    )
                )
//                showAdd()
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String?,
                error: UnityAds.UnityAdsLoadError?,
                message: String?
            ) {
                adRequestListener.onError(message ?: "Error: $TAG")
            }

        })
    }

    override fun showAdd(activity: Activity, placementId: String, adShowListener: IAdShowListener) {
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