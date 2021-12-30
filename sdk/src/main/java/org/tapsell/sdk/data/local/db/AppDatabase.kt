package org.tapsell.sdk.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.tapsell.sdk.data.local.db.user.WaterfallDao
import org.tapsell.sdk.data.local.db.user.WaterfallEntity


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
