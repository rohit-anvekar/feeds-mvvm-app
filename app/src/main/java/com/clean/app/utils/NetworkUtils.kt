package com.clean.app.utils

import android.util.Log
import com.clean.app.data.remote.ApiResponse
import java.io.IOException

/**
 * Created by rohit.anvekar on 14/7/20.
 */
suspend fun <T: Any> apiCall(
    call: suspend () -> ApiResponse<T>,
    errorMessage: String
): ApiResponse<T>{
    return try {
        call()
    }catch (e: Exception){
        Log.e("apiCall","${e.message}")
        ApiResponse.Exception(IOException(errorMessage, e))
    }
}
