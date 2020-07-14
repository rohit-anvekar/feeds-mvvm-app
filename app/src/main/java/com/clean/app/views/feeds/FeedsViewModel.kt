package com.clean.app.views.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clean.app.data.entity.Feeds
import com.clean.app.data.remote.ApiResponse
import com.clean.app.domain.FeedsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsViewModel @Inject constructor(private val feedsUseCase: FeedsUseCase) :
    ViewModel() {
    private val _feeds = MutableLiveData<Feeds>()
    val feeds: LiveData<Feeds>
        get() = _feeds
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private var job: Job? = null

    /**
     * getFeeds() func fetch the latest feeds from api.
     * @paramre
     */
    fun getFeeds(url: String) {
        _isLoading.value = true
        job = CoroutineScope(Dispatchers.Default).launch {
            val result =  feedsUseCase(url)
            _isLoading.postValue(false)
            when (result) {
                is ApiResponse.Success -> _feeds.postValue(result.items)
                is ApiResponse.Error -> _error.postValue(result.errorMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}