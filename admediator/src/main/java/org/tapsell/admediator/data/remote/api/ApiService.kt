package org.tapsell.admediator.data.remote.api


import org.tapsell.admediator.data.model.response.AdNetworkResponse
import org.tapsell.admediator.data.model.response.RewardedWaterfallResponse
import org.tapsell.admediator.utils.Constants
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    /**
     * get all AdNetworks list
     */
    @GET(Constants.ENDPOINT_AD_NETWORKS)
    suspend fun getAdNetworks(): Response<AdNetworkResponse>

    /**
     * get all Rewarded Waterfalls list
     */
    @GET(Constants.ENDPOINT_REWARDED_WATERFALL)
    suspend fun getRewardedWaterfalls(): Response<RewardedWaterfallResponse>

}
