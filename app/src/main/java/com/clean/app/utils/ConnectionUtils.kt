package com.clean.app.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build


/**
 * Created by rohit.anvekar on 29/6/20.
 */
object ConnectionUtils {

    var isNetworkAvailableOnAndroidP: Boolean = false

    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailableOnAndroidP
    }

    /**
     * Function : checkNetworkAvailability for checking
     * network availability at application level
     */
    fun checkNetworkAvailability(context: Context) {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder: NetworkRequest.Builder = NetworkRequest.Builder()
            cm.registerNetworkCallback(
                builder.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                            isNetworkAvailableOnAndroidP = true
                    }

                    override fun onLost(network: Network) {
                            isNetworkAvailableOnAndroidP = false
                    }
                }
            )
        }
    }

}
