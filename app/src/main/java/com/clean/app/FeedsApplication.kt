package com.clean.app

import android.app.Application
import com.clean.app.common.components.ApplicationComponent

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

}