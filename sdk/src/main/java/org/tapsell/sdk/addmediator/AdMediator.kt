package org.tapsell.sdk.addmediator

import android.app.Activity
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.tapsell.sdk.addmediator.tapsell.TapsellAdsManager
import org.tapsell.sdk.addmediator.unity.UnityAdsManager
import org.tapsell.sdk.data.datasource.WaterfallDataSource
import org.tapsell.sdk.data.local.db.waterfall.WaterfallEntity
import org.tapsell.sdk.data.model.enums.AdType
import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.model.response.AdRequestResponse
import org.tapsell.sdk.data.repository.AdNetworkRepositoryImpl
import org.tapsell.sdk.di.DatabaseModule


class AdMediator private constructor() : IAdMediator {

    companion object {
        const val TAG = "AddMediator"

        @Volatile
        private var INSTANCE: AdMediator? = null

        @Synchronized
        fun getInstance(): AdMediator =
            INSTANCE ?: AdMediator().also { INSTANCE = it }
    }

    override fun initialize(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            initializeDatabase(context)

            fetchAndInitializeAdNetworks(context)

            deleteAllExpiredWaterfalls()

            fetchAndStoreWaterfallsIfEmpty()
        }

    }

    override fun requestAd(activity: Activity) {
        CoroutineScope(Dispatchers.IO).launch {
            WaterfallDataSource.getActiveWaterfallOrNull()?.let { waterfall: WaterfallEntity ->
                when (waterfall.name) {
                    AdType.UNITY.title -> {
                        UnityAdsManager.requestAd(waterfall.id, object : IAdRequestListener {
                            override fun onSuccess(response: AdRequestResponse) {
                                Log.i(TAG, "requestAd: Success:${response.toString()}")
                            }

                            override fun onError(e: String) {
                                Log.i(TAG, "Error: requestAd:$e")
                            }
                        })
                    }
                    AdType.TAPSELL.title -> {
                        TapsellAdsManager.requestAd(
                            activity,
                            waterfall.id,
                            object : IAdRequestListener {
                                override fun onSuccess(response: AdRequestResponse) {
                                    Log.i(TAG, "requestAd: Success:${response.toString()}")
                                }

                                override fun onError(e: String) {
                                    Log.i(TAG, "Error: requestAd:$e")
                                }
                            })
                    }
                    else -> {
                        throw IllegalStateException("this ad type does not specified")
                    }
                }
            }
        }
    }

    override fun showAdd(activity: Activity) {
    }

    private suspend fun fetchAndInitializeAdNetworks(context: Context) {
        val adNetworks = AdNetworkRepositoryImpl.getAdNetworks()
        adNetworks.onEach { adNetworkResponse: AdNetworkItem ->
            with(adNetworkResponse) {
                initializeAdNetworks(context, name, id)
            }
        }.catch { e: Throwable ->
            Log.e("Error: $TAG :", e.message?.toString() ?: "")
        }.collect()
    }

    private fun initializeAdNetworks(
        context: Context,
        adNetworkName: String,
        adNetworkId: String
    ) {
        when (adNetworkName) {
            AdType.UNITY.title -> {
                UnityAdsManager.initialize(context, adNetworkId)
            }
            AdType.TAPSELL.title -> {
                TapsellAdsManager.initialize(context, adNetworkId)
            }
            else -> {
                throw IllegalStateException("this ad type does not specified")
            }
        }
    }

    private fun initializeDatabase(context: Context) {
        DatabaseModule.provideDatabase(context)
    }

    private suspend fun fetchAndStoreWaterfallsIfEmpty() {
        if (WaterfallDataSource.isActiveWaterfall()) {
            return
        }
        WaterfallDataSource.fetchAndStore()
    }

    private suspend fun deleteAllExpiredWaterfalls() {
        WaterfallDataSource.deleteAllExpiredRecords()
    }
}