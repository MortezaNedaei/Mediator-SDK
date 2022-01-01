package org.tapsell.admediator.main

import org.tapsell.admediator.data.model.response.AdShowResponse

interface IAdShowListener {
    fun onSuccess(response: AdShowResponse)
    fun onError(e: String)
}