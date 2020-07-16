package com.clean.app.common.coroutine

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by rohit.anvekar on 16/7/20.
 */
open class TestContextProvider : CoroutineContextProvider() {

   override  val MAIN: CoroutineContext by lazy { Dispatchers.Main }
   override open val IO: CoroutineContext by lazy { Dispatchers.IO }

}