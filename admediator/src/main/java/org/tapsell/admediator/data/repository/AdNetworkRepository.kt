package org.tapsell.admediator.data.repository


import kotlinx.coroutines.flow.Flow
import org.tapsell.admediator.data.model.response.AdNetworkItem


interface AdNetworkRepository {
    suspend fun getAdNetworks(): Flow<AdNetworkItem>
}
