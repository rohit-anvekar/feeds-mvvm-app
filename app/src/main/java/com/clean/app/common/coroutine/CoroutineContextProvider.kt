package com.clean.app.common.coroutine

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by rohit.anvekar on 16/7/20.
 */
open class CoroutineContextProvider @Inject constructor() {

    open val MAIN: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }

}