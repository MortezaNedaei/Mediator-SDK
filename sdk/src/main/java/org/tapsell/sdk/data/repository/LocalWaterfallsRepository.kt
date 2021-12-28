package org.tapsell.sdk.data.repository


import androidx.lifecycle.LiveData
import org.tapsell.sdk.data.local.db.user.WaterfallEntity


interface LocalWaterfallsRepository {
    suspend fun getAll(): LiveData<List<WaterfallEntity>>
    suspend fun getItem(id: String): WaterfallEntity?
    suspend fun insertAll(list: List<WaterfallEntity>)
    suspend fun insert(waterfall: WaterfallEntity)
    suspend fun update(waterfall: WaterfallEntity)
    suspend fun delete(waterfall: WaterfallEntity)
    suspend fun deleteAll()
}
