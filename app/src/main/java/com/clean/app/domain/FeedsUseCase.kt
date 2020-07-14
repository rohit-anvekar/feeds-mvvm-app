package com.clean.app.domain

import com.clean.app.data.entity.Feeds
import com.clean.app.data.remote.ApiResponse
import com.clean.app.data.repository.FeedsRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rohit.anvekar on 14/7/20.
 */
@Singleton
class FeedsUseCase @Inject constructor(private val feedsRepositoryImpl: FeedsRepositoryImpl) {

    suspend operator fun invoke(url:String): ApiResponse<Feeds> {
        return feedsRepositoryImpl.getFeeds(url)
    }
}