package com.clean.app.views.feeds

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.clean.app.R
import com.clean.app.utils.TestUtils
import com.clean.app.view.feeds.IdlingResourceRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.Not


/**
 * Created by rohit.anvekar on 15/7/20.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class FeedsActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(FeedsActivity::class.java)

    private val appTitle = "About Canada"
    private val scrollToPosition10: Int = 10
    private val scrollToPosition1: Int = 1
    private val scrollToPosition14: Int = 14


    @Test
    fun `test_app_title_visibility`() {
        onView(withId(R.id.appTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun `test_feed_recycle_view_visibility`() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun `test_feed_progress_bar_visiblity`() {
        onView(withId(R.id.progressBar)).check(matches(Matchers.not(isDisplayed())))
    }

    @Test
    fun `test_load_feeds_button_visiblity`() {
        onView(withId(R.id.btnLoadFeeds)).check(matches(isDisplayed()))
    }

    @Test
    fun `test_load_feeds_button_click`() {
        onView(withId(R.id.btnLoadFeeds)).perform(click())
    }

    @Test
    fun `test_feed_load_and_display_and_scrolling_to_position`() {
        onView(withId(R.id.btnLoadFeeds)).perform(click())
        val idlingResourceRule = IdlingResourceRule(activityRule, R.id.progressBar)
        IdlingRegistry.getInstance().register(idlingResourceRule)
        onView(withId(R.id.appTitle)).check(matches(withText(appTitle)))
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FeedsAdapter.FeedsViewHolder>(
                scrollToPosition10,
                ViewActions.scrollTo()
            )
        )

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FeedsAdapter.FeedsViewHolder>(
                scrollToPosition1, ViewActions.scrollTo()
            )
        )
        IdlingRegistry.getInstance().unregister(idlingResourceRule)
    }

    @Test
    fun `test_feed_pull_to_refresh_and_count_the_item`() {
        val idlingResourceRule = IdlingResourceRule(activityRule, R.id.progressBar)
        IdlingRegistry.getInstance().register(idlingResourceRule)
        onView(withId(R.id.swipeRefreshView)).perform(TestUtils.pullSwipeRefreshView())
        onView(withId(R.id.recycler_view)).check(matches((TestUtils.hasItemCount(scrollToPosition14))))
        IdlingRegistry.getInstance().unregister(idlingResourceRule)
    }


}