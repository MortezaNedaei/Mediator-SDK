package org.tapsell.sdk.data.local.db.waterfall

import androidx.room.*
import org.tapsell.sdk.utils.Constants


@Dao
interface WaterfallDao {

    @Query("SELECT * FROM waterfall")
    fun getAll(): List<WaterfallEntity>

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

    @Query("Delete FROM waterfall  WHERE strftime('%s', 'now') - timestamp / 1000 >= ${Constants.EPOCH_HOUR_SECONDS}")
    suspend fun deleteAll()
}
