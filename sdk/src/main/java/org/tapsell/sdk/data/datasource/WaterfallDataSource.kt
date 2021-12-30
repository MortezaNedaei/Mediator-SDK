package org.tapsell.sdk.data.datasource

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import org.tapsell.sdk.data.local.db.user.WaterfallEntity
import org.tapsell.sdk.data.model.response.WaterfallResponse
import org.tapsell.sdk.data.model.response.toLocalEntity
import org.tapsell.sdk.data.repository.LocalWaterfallRepositoryImpl
import org.tapsell.sdk.data.repository.RemoteWaterfallRepositoryImpl


internal object WaterfallDataSource {

    suspend fun fetchAndStore() {
        val remoteList: Flow<WaterfallResponse> = fetchAll()
        storeAll(remoteList)
    }

    private suspend fun fetchAll(): Flow<WaterfallResponse> {
        return RemoteWaterfallRepositoryImpl.getRewardedWaterfalls()
    }

    private suspend fun storeAll(flowList: Flow<WaterfallResponse>) {
        LocalWaterfallRepositoryImpl.insertAll(flowList.toLocalEntity())
    }

    suspend fun readAllRecords(): LiveData<List<WaterfallEntity>> {
        return LocalWaterfallRepositoryImpl.getAll()
    }

    suspend fun getRecord(id: String): WaterfallEntity? {
        return LocalWaterfallRepositoryImpl.getItem(id)
    }

    suspend fun store(waterfall: WaterfallResponse) {
        LocalWaterfallRepositoryImpl.insert(waterfall.toLocalEntity())
    }

    suspend fun update(waterfall: WaterfallEntity) {
        LocalWaterfallRepositoryImpl.update(waterfall)
    }

    suspend fun deleteRecord(waterfall: WaterfallEntity) {
        return LocalWaterfallRepositoryImpl.delete(waterfall)
    }

    suspend fun deleteAllRecords() {
        return LocalWaterfallRepositoryImpl.deleteAll()
    }
}
