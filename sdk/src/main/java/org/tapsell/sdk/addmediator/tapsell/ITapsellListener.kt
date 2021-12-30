package org.tapsell.sdk.addmediator.tapsell

import android.app.Activity
import android.content.Context
import org.tapsell.sdk.BuildConfig
import org.tapsell.sdk.addmediator.IAdRequestListener
import org.tapsell.sdk.addmediator.IAdShowListener

interface ITapsellListener {

    fun initialize(
        context: Context,
        tapsellKey: String = BuildConfig.TAPSELL_KEY,
    )

    fun requestAd(
        activity: Activity,
        tapsellKey: String = BuildConfig.TAPSELL_KEY,
        adRequestListener: IAdRequestListener,
    )

    fun showAdd(
        activity: Activity,
        tapsellKey: String = BuildConfig.TAPSELL_KEY,
        adShowListener: IAdShowListener,
    )
}