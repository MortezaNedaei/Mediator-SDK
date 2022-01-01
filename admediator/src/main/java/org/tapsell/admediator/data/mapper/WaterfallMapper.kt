package org.tapsell.admediator.data.mapper

import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.model.response.WaterfallResponse


class WaterfallMapper : Mapper<WaterfallResponse, WaterfallEntity> {

    override fun map(input: WaterfallResponse): WaterfallEntity =
        WaterfallEntity(
            id = input.id,
            name = input.name,
            timestamp = System.currentTimeMillis(),
        )
}
