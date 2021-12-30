package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.tapsell.sdk.data.model.enums.AdType

@Parcelize
data class AdRequestResponse(
    val id: String? = null,
    val zone: String? = null,
    val adType: AdType
) : Parcelable