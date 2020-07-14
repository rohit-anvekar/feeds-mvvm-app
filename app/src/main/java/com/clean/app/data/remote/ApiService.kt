package com.clean.app.data.remote

import com.clean.app.data.entity.Feeds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by rohit.anvekar on 14/7/20.
 */
interface ApiService {
    @GET
    suspend fun getFeeds(@Url url: String): Response<Feeds>
}