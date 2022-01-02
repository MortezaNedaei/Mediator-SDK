package org.tapsell.admediator.data.local.db

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.tapsell.admediator.data.local.db.waterfall.WaterfallDao
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.model.enums.AdType
import org.tapsell.admediator.data.repository.LocalWaterfallRepositoryImpl

@ExperimentalCoroutinesApi
class LocalDataSourceImplTest {

    private val waterfallDao = mockk<WaterfallDao>()
    private val dataSource = mockk<LocalWaterfallRepositoryImpl>(relaxed = true)

    @MockK
    private val mockWaterfalls = listOf(
        WaterfallEntity(
            id = AdType.TAPSELL.name,
            name = AdType.TAPSELL.title,
            timestamp = System.currentTimeMillis()
        )
    )


    @Test
    fun `should verify insertAll into table behaviour`() = runTest {
        dataSource.insertAll(mockWaterfalls)
        coEvery { waterfallDao.insertAll(mockWaterfalls) }
    }

    @Test
    fun `should verify clear table behaviour`() = runTest {
        dataSource.deleteAll()
        coEvery { waterfallDao.deleteAll() }
    }


    @Test
    fun `should verify getAllWaterfalls behaviour`() = runTest {
        dataSource.getAll()
        coEvery { waterfallDao.getAll() }
    }

    @Test
    fun `should verify behaviour`() = runTest {
        dataSource.getItem(AdType.TAPSELL.name)
        coEvery { waterfallDao.getItem(AdType.TAPSELL.name) }
    }
}