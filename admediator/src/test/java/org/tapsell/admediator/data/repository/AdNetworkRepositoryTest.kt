package org.tapsell.admediator.data.repository

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.tapsell.admediator.data.model.enums.AdType
import org.tapsell.admediator.data.model.response.AdNetworkItem
import org.tapsell.admediator.utils.CoroutineTestRule

@ExperimentalCoroutinesApi
class AdNetworkRepositoryTest {

    private val repo = mockk<AdNetworkRepositoryImpl>()

    @get:Rule
    val rule = CoroutineTestRule()

    @Test(expected = RuntimeException::class)
    fun `should throw when type doesn't exist`() = runTest {
        repo.getAdNetworks().onEach { adNetworkItem: AdNetworkItem ->
            assertEquals(
                (adNetworkItem.name === AdType.TAPSELL.title),
                (adNetworkItem.name === AdType.UNITY.title)
            )
        }
    }
}