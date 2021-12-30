package org.tapsell.sdk.addmediator

import org.tapsell.sdk.data.model.response.AdRequestResponse

interface IAdRequestListener {
    fun onSuccess(response: AdRequestResponse)
    fun onError(e: String)
}