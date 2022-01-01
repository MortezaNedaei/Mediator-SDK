package org.tapsell.admediator.data.repository


import kotlinx.coroutines.flow.Flow
import org.tapsell.admediator.data.model.response.WaterfallResponse


interface RemoteWaterfallRepository {
    suspend fun getRewardedWaterfalls(): Flow<WaterfallResponse>
}
