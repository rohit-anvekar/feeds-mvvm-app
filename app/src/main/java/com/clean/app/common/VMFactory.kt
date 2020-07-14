package com.clean.app.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class VMFactory<VM : ViewModel> @Inject constructor(
    private val viewModel: Provider<VM>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel.get() as T
}