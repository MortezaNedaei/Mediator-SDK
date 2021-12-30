package org.tapsell.sdk.data.repository


import kotlinx.coroutines.flow.Flow
import org.tapsell.sdk.data.model.response.AdNetworkItem


interface AdNetworkRepository {
    suspend fun getAdNetworks(): Flow<AdNetworkItem>
}
