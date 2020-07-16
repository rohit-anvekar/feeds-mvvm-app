package com.clean.app.view.feeds

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.IdlingResource
import androidx.test.rule.ActivityTestRule
import com.clean.app.views.feeds.FeedsActivity


class IdlingResourceRule(val activityRule: ActivityTestRule<FeedsActivity> ,val id: Int) : IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return "view_id-$id"
    }

    override fun isIdleNow(): Boolean {
        val isIdle = activityRule.activity.findViewById<ProgressBar>(id).visibility == View.GONE
        if (isIdle) {
            callback?.onTransitionToIdle();
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}



