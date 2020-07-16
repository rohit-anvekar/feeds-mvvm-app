package com.clean.app.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Created by rohit.anvekar on 15/7/20.
 */
object TestUtils {
    @JvmStatic
    fun hasItemCount(itemCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(
            RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has $itemCount items")
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.itemCount == itemCount
            }
        }
    }

    @JvmStatic
    fun pullSwipeRefreshView(): ViewAction? {
        return GeneralSwipeAction(
            Swipe.FAST,
            GeneralLocation.TOP_CENTER,
            GeneralLocation.BOTTOM_CENTER,
            Press.FINGER
        )
    }
}