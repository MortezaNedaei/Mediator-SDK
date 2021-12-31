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
import org.tapsell.sdk.data.model.response.AdShowResponse
import org.tapsell.sdk.data.repository.AdNetworkRepositoryImpl
import org.tapsell.sdk.di.DatabaseModule


class AdMediator private constructor() : IAdMediator {

    companion object {
        const val TAG = "AddMediator"

        var activeAdRequest: AdRequestResponse? = null

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

    override fun requestAd(activity: Activity, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            WaterfallDataSource.getActiveWaterfallOrNull()?.let { waterfall: WaterfallEntity ->
                val adRequestListener = object : IAdRequestListener {
                    override fun onSuccess(response: AdRequestResponse) {
                        Log.i(TAG, "requestAd: Success:${response.toString()}")
                        activeAdRequest = response
                        onResult(true)
                    }

                    override fun onError(e: String) {
                        Log.i(TAG, "Error: requestAd:$e")
                        CoroutineScope(Dispatchers.IO).launch {
                            WaterfallDataSource.deleteRecord(waterfall)
                            requestAd(activity, onResult)
                            onResult(false)
                        }
                    }
                }
                when (waterfall.name) {
                    AdType.UNITY.title -> {
                        UnityAdsManager.requestAd(waterfall.id, adRequestListener)
                    }
                    AdType.TAPSELL.title -> {
                        TapsellAdsManager.requestAd(activity, waterfall.id, adRequestListener)
                    }
                    else -> {
                        throw IllegalStateException("this ad type does not specified")
                    }
                }
            } ?: fetchAndStoreWaterfallsIfEmpty()
        }
    }

    override fun showAdd(activity: Activity) {
        activeAdRequest?.let { activeAd ->
            val adShowListener = object: IAdShowListener {
                override fun onSuccess(response: AdShowResponse) {
                    Log.i(TAG, "showAdd: Success:${response.toString()}")
                }

                override fun onError(e: String) {
                    Log.i(TAG, "Error: showAdd:$e")
                }
            }
            when (activeAd.adType) {
                AdType.UNITY -> {
                    UnityAdsManager.showAdd(activity, activeAd.id!!, adShowListener)
                }
                AdType.TAPSELL -> {
                    TapsellAdsManager.showAdd(activity, activeAd.id!!, adShowListener)
                }
                else -> {
                    throw IllegalStateException("this ad type does not specified")
                }
            }
        }
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
        if (WaterfallDataSource.getActiveWaterfallOrNull() !== null) {
            return
        }
        WaterfallDataSource.fetchAndStore()
    }

    private suspend fun deleteAllExpiredWaterfalls() {
        WaterfallDataSource.deleteAllExpiredRecords()
    }
}