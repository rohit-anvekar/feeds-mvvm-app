package com.clean.app.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by rohit.anvekar on 15/7/20.
 */
@RunWith(AndroidJUnit4::class)
class ConnectionUtilsTest{

    @Test
    fun `test_is_network_available`(){
        assertTrue(ConnectionUtils.isNetworkAvailable())
    }
}