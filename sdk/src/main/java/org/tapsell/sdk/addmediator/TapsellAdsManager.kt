package org.tapsell.sdk.addmediator

import android.content.Context
import android.util.Log
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusInitListener
import ir.tapsell.plus.model.AdNetworkError
import ir.tapsell.plus.model.AdNetworks

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


    override fun requestAd() {
    }

    override fun showAdd() {
    }

}