package com.clean.app.data.repository

import com.clean.app.BuildConfig
import com.clean.app.data.entity.Feeds
import com.clean.app.data.entity.Row
import com.clean.app.data.remote.ApiResponse
import com.clean.app.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsRepositoryImplTest {

    private var feedsRepositoryImpl: FeedsRepositoryImpl? = null
    private val mockApiService: ApiService = mock(ApiService::class.java)
    private val feeds: Feeds =
        Feeds(
            "title",
            listOf(Row("title", "description", "url"))
        )


    @Before
    fun setUp() {
        feedsRepositoryImpl = FeedsRepositoryImpl(mockApiService)
    }

    @Test
    fun `get feeds from remote api success`() {
        runBlocking {
            val response = Response.success(feeds)

            Mockito.`when`(mockApiService.getFeeds(BuildConfig.FEED_URL))
                .thenReturn(response)
            val result = feedsRepositoryImpl?.getFeeds(BuildConfig.FEED_URL)
            Mockito.verify(mockApiService).getFeeds(BuildConfig.FEED_URL)
            assertEquals(ApiResponse.Success(feeds), result)
        }
    }

    @After
    fun tearDown() {
        feedsRepositoryImpl = null
    }
}

