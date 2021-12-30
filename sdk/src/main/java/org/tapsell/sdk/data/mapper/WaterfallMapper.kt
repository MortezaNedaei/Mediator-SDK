package org.tapsell.sdk.data.mapper

import org.tapsell.sdk.data.local.db.user.WaterfallEntity
import org.tapsell.sdk.data.model.response.WaterfallResponse


class WaterfallMapper : Mapper<WaterfallResponse, WaterfallEntity> {

    override fun map(input: WaterfallResponse): WaterfallEntity =
        WaterfallEntity(
            id = input.id,
            name = input.name,
            timestamp = System.currentTimeMillis(),
        )
}
