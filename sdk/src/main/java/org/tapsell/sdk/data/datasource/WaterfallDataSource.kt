package org.tapsell.sdk.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import org.tapsell.sdk.addmediator.extensions.isNullOrEmpty
import org.tapsell.sdk.data.local.db.waterfall.WaterfallEntity
import org.tapsell.sdk.data.model.response.WaterfallResponse
import org.tapsell.sdk.data.model.response.toLocalEntity
import org.tapsell.sdk.data.repository.LocalWaterfallRepositoryImpl
import org.tapsell.sdk.data.repository.RemoteWaterfallRepositoryImpl
import org.tapsell.sdk.utils.Utils.isWaterfallExpired


internal object WaterfallDataSource {

    val TAG = this::class.java.simpleName

    suspend fun fetchAndStore() {
        val remoteList: Flow<WaterfallResponse> = fetchAll()
        storeAll(remoteList)
    }

    suspend fun getActiveWaterfallOrNull(): WaterfallEntity? {
        var activeWaterfall: WaterfallEntity? = null
        readAllRecords().filterNotNull().onEach { waterfalls: List<WaterfallEntity> ->
            waterfalls.forEach { waterfall: WaterfallEntity ->
                if (isWaterfallExpired(waterfall.timestamp)) {
                    // delete from db if expired
                    deleteRecord(waterfall)
                } else {
                    activeWaterfall = waterfall
                    return@onEach
                }
            }
        }
        return activeWaterfall
    }

    private suspend fun fetchAll(): Flow<WaterfallResponse> {
        return RemoteWaterfallRepositoryImpl.getRewardedWaterfalls()
    }

    private suspend fun storeAll(flowList: Flow<WaterfallResponse>) {
        LocalWaterfallRepositoryImpl.insertAll(flowList.toLocalEntity())
    }

    private suspend fun readAllRecords(): Flow<List<WaterfallEntity>> {
        return LocalWaterfallRepositoryImpl.getAll()
    }

    private suspend fun deleteRecord(waterfall: WaterfallEntity) {
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
            .isNullOrEmpty()
    }


    suspend fun isActiveWaterfall(): Boolean {
        return getActiveWaterfallOrNull() !== null
    }
}
