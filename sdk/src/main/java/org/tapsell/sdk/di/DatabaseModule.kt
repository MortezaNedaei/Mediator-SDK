package org.tapsell.sdk.di

import android.content.Context
import androidx.room.Room
import org.tapsell.sdk.data.local.db.AppDatabase
import org.tapsell.sdk.data.local.db.waterfall.WaterfallDao
import org.tapsell.sdk.utils.Constants


object DatabaseModule {

    private lateinit var db: AppDatabase

    fun provideDatabase(applicationContext: Context): AppDatabase {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Constants.DBName
        ).build()
        return db
    }

    private fun getInstance(): AppDatabase {
        return db
    }

    fun provideWaterfallDao(): WaterfallDao = getInstance().waterfallDao()
}
