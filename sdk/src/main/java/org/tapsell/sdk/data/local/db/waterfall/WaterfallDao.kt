package org.tapsell.sdk.data.local.db.waterfall

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WaterfallDao {

    @Query("SELECT * FROM waterfall")
    fun getAll(): Flow<List<WaterfallEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<WaterfallEntity>)

    @Query("SELECT * FROM waterfall WHERE id LIKE :id")
    suspend fun getItem(id: String): WaterfallEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(waterfallEntity: WaterfallEntity)

    @Update
    suspend fun update(waterfallEntity: WaterfallEntity)

    @Delete
    suspend fun delete(waterfallEntity: WaterfallEntity)

    @Query("Delete FROM waterfall  WHERE timestamp <= datetime('now', '-1 hours')")
    suspend fun deleteAll()
}
