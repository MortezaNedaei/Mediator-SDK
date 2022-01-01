package org.tapsell.admediator.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.parcelize.Parcelize
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.data.mapper.WaterfallMapper

@Parcelize
data class RewardedWaterfallResponse(
    @Json(name = "type")
    val type: String,
    @Json(name = "waterfall")
    val waterfall: List<WaterfallResponse>
) : Parcelable {

    fun toLocalModel(): List<WaterfallEntity> {
        val localList = mutableListOf<WaterfallEntity>()
        this.waterfall.forEach { result: WaterfallResponse ->
            localList.add(WaterfallMapper().map(result))
        }
        return localList
    }
}

suspend fun Flow<WaterfallResponse>.toLocalEntity(): List<WaterfallEntity> {
    val localList = mutableListOf<WaterfallEntity>()
    this.onEach { result: WaterfallResponse ->
        localList.add(result.toLocalEntity())
    }.collect()
    return localList
}