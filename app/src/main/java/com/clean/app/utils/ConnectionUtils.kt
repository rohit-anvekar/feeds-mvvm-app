package com.clean.app.utils


import android.content.Context


/**
 * Created by rohit.anvekar on 29/6/20.
 */
object ConnectionUtils {

    var isNetworkAvailableOnAndroidP: Boolean = false

    @SuppressWarnings("unchecked")
    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailableOnAndroidP
    }
}
