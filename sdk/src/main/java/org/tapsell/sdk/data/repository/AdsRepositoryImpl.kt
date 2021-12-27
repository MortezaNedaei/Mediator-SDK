package org.tapsell.sdk.data.repository

import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.model.response.WaterfallResponse
import org.tapsell.sdk.data.remote.api.ApiService
import org.tapsell.sdk.di.ApiServiceModule


object AdsRepositoryImpl : AdsRepository {

    private val apiService: ApiService by lazy { ApiServiceModule.provideApiService() }

    override suspend fun getAdNetworks(): List<AdNetworkItem> {
        val response = apiService.getAdNetworks()
        return if (response.isSuccessful) response.body()?.adNetworks
            ?: emptyList() else emptyList()
    }

    override suspend fun getRewardedWaterfalls(): List<WaterfallResponse> {
        val response = apiService.getRewardedWaterfalls()
        return if (response.isSuccessful) response.body()?.waterfall ?: emptyList() else emptyList()
    }

}
