package org.tapsell.admediator.data.mapper

import  org.junit.Assert.assertEquals
import org.junit.Test
import org.tapsell.admediator.data.model.enums.AdType
import org.tapsell.admediator.data.model.response.WaterfallResponse

class MappersTest {

    @Test
    fun `should successfully convert network waterfall to local waterfall entity`() {
        val dto = WaterfallResponse(
            AdType.TAPSELL.name, AdType.TAPSELL.title
        )

        val entity = dto.toLocalEntity()

        assertEquals(entity.id, AdType.TAPSELL.name)
        assertEquals(entity.name, AdType.TAPSELL.title)
    }

}