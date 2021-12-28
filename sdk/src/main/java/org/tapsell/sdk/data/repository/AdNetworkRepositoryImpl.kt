package org.tapsell.sdk.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import org.tapsell.sdk.data.model.response.AdNetworkItem
import org.tapsell.sdk.data.remote.api.ApiService
import org.tapsell.sdk.di.ApiServiceModule


object AdNetworkRepositoryImpl : AdNetworkRepository {

    private val apiService: ApiService by lazy { ApiServiceModule.provideApiService() }

    override suspend fun getAdNetworks(): Flow<AdNetworkItem> {
        val response = apiService.getAdNetworks()
        return if (response.isSuccessful) response.body()?.adNetworks?.asFlow()
            ?: emptyFlow() else emptyFlow()
    }

}
