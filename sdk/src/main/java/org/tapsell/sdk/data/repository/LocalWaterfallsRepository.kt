package org.tapsell.sdk.data.repository


import kotlinx.coroutines.flow.Flow
import org.tapsell.sdk.data.local.db.waterfall.WaterfallEntity


interface LocalWaterfallsRepository {
    suspend fun getAll(): Flow<List<WaterfallEntity>>
    suspend fun getItem(id: String): WaterfallEntity?
    suspend fun insertAll(list: List<WaterfallEntity>)
    suspend fun insert(waterfall: WaterfallEntity)
    suspend fun update(waterfall: WaterfallEntity)
    suspend fun delete(waterfall: WaterfallEntity)
    suspend fun deleteAll()
}
