package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RewardedWaterfallResponse(
    @Json(name = "type")
    val type: String,
    @Json(name = "waterfall")
    val waterfall: List<WaterfallResponse>
) : Parcelable