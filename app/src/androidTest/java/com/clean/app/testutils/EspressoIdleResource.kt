package com.clean.app.testutils

/**
 * Created by rohit.anvekar on 15/7/20.
 */
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdleResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}