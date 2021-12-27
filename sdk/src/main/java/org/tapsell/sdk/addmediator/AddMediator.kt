package org.tapsell.sdk.addmediator

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tapsell.sdk.data.model.enums.AdType
import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.repository.AdsRepositoryImpl


class AddMediator constructor(
    private val context: Context
) : IAddMediator {


//    companion object {
//
//        @JvmStatic
//        fun initialize() {
//        }
//
//        @JvmStatic
//        fun requestAdd() {
//        }
//
//        @JvmStatic
//        fun showAdd() {
//        }
//    }

    override fun initialize(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            getAndInitializeAdNetworks()
        }

    }

    override fun requestAd() {
    }

    override fun showAdd() {
    }

    private suspend fun getAndInitializeAdNetworks() {
        val adNetworks = AdsRepositoryImpl.getAdNetworks()
        adNetworks.forEach { adNetworkResponse: AdNetworkItem ->
            when (adNetworkResponse.name) {
                AdType.UNITY.title -> {
                    UnityAdsManager.initialize(context, adNetworkResponse.id)
                }
                AdType.TAPSELL.title -> {
                    TapsellAdsManager.initialize(context, adNetworkResponse.id)
                }
                else -> {
                    throw IllegalStateException("this ad type does not specified")
                }
            }
        }
    }
}