package org.tapsell.sdk.data.repository


import kotlinx.coroutines.flow.Flow
import org.tapsell.sdk.data.model.response.WaterfallResponse


interface RemoteWaterfallRepository {
    suspend fun getRewardedWaterfalls(): Flow<WaterfallResponse>
}
