package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class WaterfallResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
) : Parcelable