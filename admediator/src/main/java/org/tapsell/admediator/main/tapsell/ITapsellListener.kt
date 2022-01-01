package org.tapsell.admediator.main.tapsell

import android.app.Activity
import android.content.Context
import org.tapsell.admediator.BuildConfig
import org.tapsell.admediator.main.IAdRequestListener
import org.tapsell.admediator.main.IAdShowListener

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