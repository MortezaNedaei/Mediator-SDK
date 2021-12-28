package org.tapsell.sdk.addmediator

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.tapsell.sdk.data.datasource.WaterfallDataSource
import org.tapsell.sdk.data.model.enums.AdType
import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.repository.AdNetworkRepositoryImpl
import org.tapsell.sdk.di.DatabaseModule


class AddMediator constructor(
    private val context: Context
) : IAddMediator {


    companion object {
        const val TAG = "AddMediator"
    }

    override fun initialize(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            initializeDatabase()
            getAndInitializeAdNetworks()
            getAndStoreWaterfalls()
        }

    }

    override fun requestAd() {
    }

    override fun showAdd() {
    }

    private suspend fun getAndInitializeAdNetworks() {
        val adNetworks = AdNetworkRepositoryImpl.getAdNetworks()
        adNetworks.onEach { adNetworkResponse: AdNetworkItem ->
            with(adNetworkResponse) {
                initializeAdNetworks(name, id)
            }
        }.catch { e: Throwable ->
            Log.e("Error: $TAG :", e.message?.toString() ?: "")
        }.collect()
    }

    private fun initializeAdNetworks(adNetworkName: String, adNetworkId: String) {
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

    private fun initializeDatabase() {
        DatabaseModule.provideDatabase(context)
    }

    private suspend fun getAndStoreWaterfalls() {
        WaterfallDataSource.fetchAndStore()
    }
}