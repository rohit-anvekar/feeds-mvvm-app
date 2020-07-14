package com.clean.app.common

import com.clean.app.FeedsApplication
/**
 * Created by rohit.anvekar on 14/7/20.
 */
class Injector {
    companion object {
        fun get() = FeedsApplication.applicationComponent
    }
}