package org.tapsell.sdk.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.tapsell.sdk.data.local.db.waterfall.WaterfallDao
import org.tapsell.sdk.data.local.db.waterfall.WaterfallEntity


@Database(
    entities = [
        WaterfallEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun waterfallDao(): WaterfallDao

}
