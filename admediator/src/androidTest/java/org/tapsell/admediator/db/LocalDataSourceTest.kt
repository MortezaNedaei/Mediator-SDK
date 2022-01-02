package org.tapsell.admediator.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.tapsell.admediator.data.local.db.AppDatabase
import org.tapsell.admediator.data.local.db.waterfall.WaterfallDao
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.model.enums.AdType
import org.tapsell.admediator.utils.Constants
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    @MockK
    private lateinit var db: AppDatabase

    @MockK
    private lateinit var waterfallDao: WaterfallDao


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        waterfallDao = db.waterfallDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun shouldSaveListAndFindById() = runBlocking {
        val list = listOf<WaterfallEntity>(
            WaterfallEntity(
                AdType.TAPSELL.name, AdType.TAPSELL.title, System.currentTimeMillis()
            )
        )

        waterfallDao.insertAll(list)

        val item = waterfallDao.getItem(AdType.TAPSELL.name)
        Assert.assertNotNull(item)
    }

    @Test
    fun shouldSaveItemAndUpdateItemToExpireAndDeleteAllExpires() = runBlocking {
        val currentTimeEpoch = System.currentTimeMillis()
        val previousHourAgoEpoch = currentTimeEpoch - Constants.EPOCH_HOUR_MILLISECONDS
        val waterfall = WaterfallEntity(
                AdType.TAPSELL.name, AdType.TAPSELL.title, System.currentTimeMillis()
            )
        val expiredWaterfall = waterfall.copy(timestamp = previousHourAgoEpoch)

        waterfallDao.insert(waterfall)

        waterfallDao.update(expiredWaterfall)

        waterfallDao.deleteAll() // deleteAll() removes all expired rows (a hour ago)

        // should return null when for expired rows
        val item = waterfallDao.getItem(AdType.TAPSELL.name)
        Assert.assertNull(item)
    }

    @Test
    fun shouldReturnNullWhenDaoIsEmpty() = runBlocking {
        Assert.assertNull(waterfallDao.getItem(AdType.TAPSELL.name))
    }
}