package com.clean.app.data.remote

import com.clean.app.data.entity.Feeds
import com.clean.app.data.entity.Row
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import java.util.*

/**
 * Created by rohit.anvekar on 15/7/20.
 */
class ApiResponseTest {

    private val feeds: Feeds = Feeds(
        "title",
        listOf(Row("title", "description", "url"))
    )

    @Test
    fun `test success`(){
        val successTest = ApiResponse.Success(feeds)
        assertEquals(successTest , ApiResponse.Success(feeds))
    }
    @Test
    fun `test error`(){
        val errorTest = ApiResponse.Error(404,"Not found")
        assertEquals(errorTest , ApiResponse.Error(404,"Not found"))
    }

    @Test
    fun `test exception`(){
        val exceptionTest = ApiResponse.Exception(Exception("IO exception"))
        assertEquals(exceptionTest.toString() , ApiResponse.Exception(Exception("IO exception")).toString())
    }
}