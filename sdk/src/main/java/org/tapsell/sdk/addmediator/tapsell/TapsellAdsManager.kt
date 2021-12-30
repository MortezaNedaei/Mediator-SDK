package org.tapsell.sdk.addmediator.tapsell

import android.app.Activity
import android.content.Context
import android.util.Log
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.AdShowListener
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusInitListener
import ir.tapsell.plus.model.AdNetworkError
import ir.tapsell.plus.model.AdNetworks
import ir.tapsell.plus.model.TapsellPlusAdModel
import ir.tapsell.plus.model.TapsellPlusErrorModel
import org.tapsell.sdk.addmediator.IAdRequestListener
import org.tapsell.sdk.addmediator.IAdShowListener
import org.tapsell.sdk.data.model.enums.AdType
import org.tapsell.sdk.data.model.response.AdRequestResponse

object TapsellAdsManager : ITapsellListener {

    const val TAG = "TapsellAdsManager"

    override fun initialize(context: Context, tapsellKey: String) {
        TapsellPlus.initialize(context, tapsellKey, object : TapsellPlusInitListener {
            override fun onInitializeSuccess(adNetworkTypes: AdNetworks?) {
                Log.i(TAG, adNetworkTypes.toString())
            }

            override fun onInitializeFailed(p0: AdNetworks?, e: AdNetworkError?) {
                Log.i(TAG, e?.errorMessage ?: "")
            }

        })
    }


    override fun requestAd(
        activity: Activity,
        tapsellKey: String,
        adRequestListener: IAdRequestListener
    ) {
        TapsellPlus.requestRewardedVideoAd(
            activity,
            tapsellKey,
            object : AdRequestCallback() {
                override fun response(response: TapsellPlusAdModel?) {
                    super.response(response)
                    Log.i(TAG, "Response:${response?.toString()}")
                    adRequestListener.onSuccess(
                        AdRequestResponse(
                            id = response?.responseId ?: "",
                            adType = AdType.TAPSELL,
                            zone = response?.zoneId
                        )
                    )
                }

                override fun error(e: String?) {
                    super.error(e)
                    Log.e(TAG, "Error: requestAd: $e")
                    adRequestListener.onError(e ?: "Error $TAG")
                }
            })
    }

    override fun showAdd(activity: Activity, tapsellKey: String, adShowListener: IAdShowListener) {
        TapsellPlus.showRewardedVideoAd(
            activity,
            tapsellKey,
            object : AdShowListener() {
                override fun onOpened(response: TapsellPlusAdModel?) {
                    super.onOpened(response)
                    Log.i(TAG, "onOpened:${response?.toString()}")
                }

                override fun onClosed(response: TapsellPlusAdModel?) {
                    super.onClosed(response)
                    Log.i(TAG, "onClosed:${response?.toString()}")
                }

                override fun onRewarded(response: TapsellPlusAdModel?) {
                    super.onRewarded(response)
                    Log.i(TAG, "onRewarded:${response?.toString()}")
                }

                override fun onError(e: TapsellPlusErrorModel?) {
                    super.onError(e)
                    Log.e(TAG, "Error: showAdd: ${e?.toString()}")
                }
            })
    }

}