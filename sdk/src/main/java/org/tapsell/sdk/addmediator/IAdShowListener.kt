package org.tapsell.sdk.addmediator

import org.tapsell.sdk.data.model.response.AdShowResponse

interface IAdShowListener {
    fun onSuccess(response: AdShowResponse)
    fun onError(e: String)
}