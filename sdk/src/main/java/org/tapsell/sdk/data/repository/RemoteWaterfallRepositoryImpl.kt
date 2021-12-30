package org.tapsell.sdk.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import org.tapsell.sdk.data.model.response.WaterfallResponse
import org.tapsell.sdk.data.remote.api.ApiService
import org.tapsell.sdk.di.ApiServiceModule


object RemoteWaterfallRepositoryImpl : RemoteWaterfallRepository {

    private val apiService: ApiService by lazy { ApiServiceModule.provideApiService() }


    override suspend fun getRewardedWaterfalls(): Flow<WaterfallResponse> {
        val response = apiService.getRewardedWaterfalls()
        return if (response.isSuccessful) response.body()?.waterfall?.asFlow()
            ?: emptyFlow() else emptyFlow()
    }

}
