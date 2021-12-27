package org.tapsell.sdk.addmediator

import android.content.Context
import ir.tapsell.plus.TapsellPlusInitListener
import org.tapsell.sdk.BuildConfig

interface ITapsellListener {
    fun initialize(context: Context, tapsellKey: String = BuildConfig.TAPSELL_KEY)
    fun requestAd()
    fun showAdd()
}