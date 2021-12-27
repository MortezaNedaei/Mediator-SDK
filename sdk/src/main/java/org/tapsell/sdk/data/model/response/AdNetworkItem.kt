package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdNetworkItem(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
) : Parcelable