package org.tapsell.admediator.data.datasource

import kotlinx.coroutines.flow.Flow
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.model.response.WaterfallResponse
import org.tapsell.admediator.data.model.response.toLocalEntity
import org.tapsell.admediator.data.repository.LocalWaterfallRepositoryImpl
import org.tapsell.admediator.data.repository.RemoteWaterfallRepositoryImpl
import org.tapsell.admediator.utils.Utils.isWaterfallExpired


internal object WaterfallDataSource {

    val TAG = this::class.java.simpleName

    suspend fun fetchAndStore() {
        val remoteList: Flow<WaterfallResponse> = fetchAll()
        storeAll(remoteList)
    }

    suspend fun getActiveWaterfallOrNull(): WaterfallEntity? {
        var activeWaterfall: WaterfallEntity? = null
        readAllRecords().forEach { waterfall ->
//            waterfalls.forEach { waterfall: WaterfallEntity ->
                if (isWaterfallExpired(waterfall.timestamp)) {
                    // delete from db if expired
                    deleteRecord(waterfall)
                } else {
                    activeWaterfall = waterfall
                    return@forEach
                }
//            }
        }
        return activeWaterfall
    }

    private suspend fun fetchAll(): Flow<WaterfallResponse> {
        return RemoteWaterfallRepositoryImpl.getRewardedWaterfalls()
    }

    private suspend fun storeAll(flowList: Flow<WaterfallResponse>) {
        LocalWaterfallRepositoryImpl.insertAll(flowList.toLocalEntity())
    }

    private suspend fun readAllRecords(): List<WaterfallEntity> {
        return LocalWaterfallRepositoryImpl.getAll()
    }

    suspend fun deleteRecord(waterfall: WaterfallEntity) {
        return LocalWaterfallRepositoryImpl.delete(waterfall)
    }

    suspend fun deleteAllExpiredRecords() {
        if (isWaterfallTableNullOrEmpty()) {
            return
        }
        LocalWaterfallRepositoryImpl.deleteAll()
    }

    private suspend fun isWaterfallTableNullOrEmpty(): Boolean {
        return LocalWaterfallRepositoryImpl
            .getAll()
            .isEmpty()
    }
}
