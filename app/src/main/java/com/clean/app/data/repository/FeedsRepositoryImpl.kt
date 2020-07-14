package com.clean.app.data.repository

import com.clean.app.data.entity.Feeds
import com.clean.app.data.remote.ApiResponse
import com.clean.app.data.remote.ApiService
import com.clean.app.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rohit.anvekar on 14/7/20.
 */
@Singleton
class FeedsRepositoryImpl @Inject constructor(
    private val apiApiService: ApiService
) : FeedsRepository {

    override suspend fun getFeeds(url: String): ApiResponse<Feeds> {
        return safeApiCall(
                call = { getFeedList(url) },
                errorMessage = "Error in get feeds api call!"
            )
    }

    private suspend fun getFeedList(url:String): ApiResponse<Feeds> {
        val result = apiApiService.getFeeds(url)
        if (result.isSuccessful) {
            return ApiResponse.Success(result.body())
        }
        return ApiResponse.Error(result.code(), result.message())
    }


}