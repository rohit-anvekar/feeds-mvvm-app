package com.clean.app.views.feeds

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clean.app.BuildConfig
import com.clean.app.coroutine.TestCoroutineRule
import com.clean.app.common.coroutine.TestContextProvider
import com.clean.app.data.entity.Feeds
import com.clean.app.data.entity.Row
import com.clean.app.data.remote.ApiResponse
import com.clean.app.domain.FeedsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rohit.anvekar on 15/7/20.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedsViewModelTest {

    @Mock
    lateinit var feedUseCase: FeedsUseCase

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var viewModel: FeedsViewModel


    private val feeds: Feeds = Feeds(
        "title",
        listOf(Row("title", "description", "url"))
    )
    private val errorCode: Int = 404
    private val error: String = "api not found"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FeedsViewModel(feedUseCase, TestContextProvider())
    }

    @Test
    fun `test_feed_view_model_get_feed_success`() {
        runBlocking {

            Mockito.`when`(feedUseCase(BuildConfig.FEED_URL))
                .thenReturn(ApiResponse.Success(feeds))

            viewModel.getFeeds(BuildConfig.FEED_URL)

            assertEquals(feeds, viewModel.feeds.value)
        }
    }

    @Test
    fun `test_feed_view_model_get_feed_error`() {
        runBlocking {
            Mockito.`when`(feedUseCase(BuildConfig.FEED_URL))
                .thenReturn(ApiResponse.Error(errorCode, error))

            viewModel.getFeeds(BuildConfig.FEED_URL)

            assertEquals(error, viewModel.error.value)
        }
    }

    @After
    fun tearDown() {
    }

}