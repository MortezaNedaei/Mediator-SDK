package org.tapsell.admediator.data.repository


import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity


interface LocalWaterfallsRepository {
    suspend fun getAll(): List<WaterfallEntity>
    suspend fun getItem(id: String): WaterfallEntity?
    suspend fun insertAll(list: List<WaterfallEntity>)
    suspend fun insert(waterfall: WaterfallEntity)
    suspend fun update(waterfall: WaterfallEntity)
    suspend fun delete(waterfall: WaterfallEntity)
    suspend fun deleteAll()
}
