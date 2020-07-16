package com.clean.app.common

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clean.app.common.coroutine.CoroutineContextProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by rohit.anvekar on 14/7/20.
 */
@SuppressLint("unchecked")
class VMFactory<VM : ViewModel> @Inject constructor(
    private val viewModel: Provider<VM>,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel.get() as T
}