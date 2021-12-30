package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdNetworkResponse(
    @Json(name = "adNetworks")
    val adNetworks: List<AdNetworkItem>,
) : Parcelable