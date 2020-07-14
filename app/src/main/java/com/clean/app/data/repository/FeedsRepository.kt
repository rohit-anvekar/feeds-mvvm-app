package com.clean.app.data.repository

import com.clean.app.data.entity.Feeds
import com.clean.app.data.remote.ApiResponse

/**
 * Created by rohit.anvekar on 14/7/20.
 */
interface FeedsRepository {
    suspend fun getFeeds(url: String): ApiResponse<Feeds>
}