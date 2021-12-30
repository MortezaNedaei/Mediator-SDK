package org.tapsell.sdk.data.model.response


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdShowResponse(
    val id: String,
) : Parcelable