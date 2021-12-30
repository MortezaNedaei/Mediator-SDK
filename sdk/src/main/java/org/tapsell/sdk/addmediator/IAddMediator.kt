package org.tapsell.sdk.addmediator

import android.content.Context

interface IAddMediator {
    fun initialize(context: Context)
    fun requestAd()
    fun showAdd()
}