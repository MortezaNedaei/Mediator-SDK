package org.tapsell.sdk.data.repository


import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.model.response.WaterfallResponse


interface AdsRepository {
    suspend fun getAdNetworks(): List<AdNetworkItem>

    suspend fun getRewardedWaterfalls(): List<WaterfallResponse>
}
