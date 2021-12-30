package org.tapsell.app.ui.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.tapsell.sdk.addmediator.AddMediator


@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AddMediator
            .getInstance()
            .initialize(this)
    }
}
