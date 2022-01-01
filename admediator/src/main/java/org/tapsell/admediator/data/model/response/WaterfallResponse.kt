package org.tapsell.admediator.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.mapper.WaterfallMapper

@Parcelize
data class WaterfallResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
) : Parcelable {

    fun toLocalEntity(): WaterfallEntity {
        return WaterfallMapper().map(this)
    }
}