package org.tapsell.app.ui.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.tapsell.sdk.addmediator.AdMediator


@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AdMediator
            .getInstance()
            .initialize(this)
    }
}
