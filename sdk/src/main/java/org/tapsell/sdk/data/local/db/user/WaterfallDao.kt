package org.tapsell.sdk.data.local.db.user

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WaterfallDao {

    @Query("SELECT * FROM waterfall")
    fun getAll(): LiveData<List<WaterfallEntity>>

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

    @Query("Delete FROM waterfall")
    suspend fun deleteAll()
}
