package com.clean.app

import android.app.Application
import com.clean.app.common.components.ApplicationComponent
import com.clean.app.common.components.DaggerApplicationComponent
import com.clean.app.common.modules.ApplicationModule

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}