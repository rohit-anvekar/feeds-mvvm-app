package com.clean.app.domain

import com.clean.app.BuildConfig
import com.clean.app.data.entity.Feeds
import com.clean.app.data.entity.Row
import com.clean.app.data.remote.ApiResponse
import com.clean.app.data.repository.FeedsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsUseCaseTest {

    private var feedsUseCase: FeedsUseCase? = null
    private val mockRepo = mock(FeedsRepositoryImpl::class.java)
    private val feeds: Feeds = Feeds(
        "title",
        listOf(Row("title", "description", "url"))
    )

    @Before
    fun setUp() {
        feedsUseCase = FeedsUseCase(mockRepo)
    }

    @Test
    fun `test get feeds from api and returns success`() {
        runBlocking {
            Mockito.`when`(mockRepo.getFeeds( BuildConfig.FEED_URL))
                .thenReturn(ApiResponse.Success(feeds))
            val result = feedsUseCase?.invoke( BuildConfig.FEED_URL)
            Mockito.verify(mockRepo).getFeeds( BuildConfig.FEED_URL)
            assertEquals(result, ApiResponse.Success(feeds))
        }
    }

    @Test
    fun `test get feed from api and returns error`() {
        runBlocking {
            Mockito.`when`(mockRepo.getFeeds(BuildConfig.FEED_URL))
                .thenReturn(ApiResponse.Error(404, "error in get feeds api call"))
            val result = feedsUseCase?.invoke(BuildConfig.FEED_URL)
            Mockito.verify(mockRepo).getFeeds(BuildConfig.FEED_URL)
            assertEquals(result, ApiResponse.Error(404, "error in get feeds api call"))
        }
    }

    @Test
    fun `test get feed from api and returns exception`() {
        runBlocking {
            Mockito.`when`(mockRepo.getFeeds(BuildConfig.FEED_URL))
                .thenReturn(ApiResponse.Exception(Exception("exception")))
            val result = feedsUseCase?.invoke(BuildConfig.FEED_URL)
            Mockito.verify(mockRepo).getFeeds(BuildConfig.FEED_URL)
            assertEquals(result.toString(), ApiResponse.Exception(Exception("exception")).toString())
        }
    }


    @After
    fun tearDown() {
        feedsUseCase = null
    }

}