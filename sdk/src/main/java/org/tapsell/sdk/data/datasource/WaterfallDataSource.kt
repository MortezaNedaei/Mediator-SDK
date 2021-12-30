package org.tapsell.sdk.data.datasource

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.tapsell.sdk.data.local.db.user.WaterfallEntity
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
        readAllRecords().onEach { waterfall: WaterfallEntity ->
            if (isWaterfallExpired(waterfall.timestamp)) {
                // delete from db if expired
                deleteRecord(waterfall)
            } else {
                activeWaterfall = waterfall
                return@onEach
            }
        }.catch { e: Throwable ->
            Log.e("Error: $TAG :", e.message?.toString() ?: "")
        }.collect()
        return activeWaterfall
    }

    private suspend fun fetchAll(): Flow<WaterfallResponse> {
        return RemoteWaterfallRepositoryImpl.getRewardedWaterfalls()
    }

    private suspend fun storeAll(flowList: Flow<WaterfallResponse>) {
        LocalWaterfallRepositoryImpl.insertAll(flowList.toLocalEntity())
    }

    private suspend fun readAllRecords(): Flow<WaterfallEntity> {
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
