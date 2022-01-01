package org.tapsell.admediator.main

import org.tapsell.admediator.data.model.response.AdRequestResponse

interface IAdRequestListener {
    fun onSuccess(response: AdRequestResponse)
    fun onError(e: String)
}