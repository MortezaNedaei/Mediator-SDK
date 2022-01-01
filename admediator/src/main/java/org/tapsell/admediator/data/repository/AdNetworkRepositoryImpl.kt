package org.tapsell.admediator.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import org.tapsell.admediator.data.model.response.AdNetworkItem
import org.tapsell.admediator.data.remote.api.ApiService
import org.tapsell.admediator.di.ApiServiceModule


object AdNetworkRepositoryImpl : AdNetworkRepository {

    private val apiService: ApiService by lazy { ApiServiceModule.provideApiService() }

    override suspend fun getAdNetworks(): Flow<AdNetworkItem> {
        val response = apiService.getAdNetworks()
        return if (response.isSuccessful) response.body()?.adNetworks?.asFlow()
            ?: emptyFlow() else emptyFlow()
    }

}
