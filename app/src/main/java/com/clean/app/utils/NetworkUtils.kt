package com.clean.app.utils

import android.util.Log
import com.clean.app.data.remote.ApiResponse
import java.io.IOException

/**
 * Created by rohit.anvekar on 14/7/20.
 */
suspend fun <T: Any> safeApiCall(
    call: suspend () -> ApiResponse<T>,
    errorMessage: String
): ApiResponse<T>{
    return try {
        call()
    }catch (e: Exception){
        Log.e("safeApiCall","${e.message}")
        ApiResponse.Exception(IOException(e.message, e))
    }
}
