package com.clean.app.common.components

import com.clean.app.common.VMFactory
import com.clean.app.common.modules.ApplicationModule
import com.clean.app.common.modules.NetworkModule
import com.clean.app.views.feeds.FeedsViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by rohit.anvekar on 14/7/20.
 */
@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent{
    fun feedsViewModelFactory(): VMFactory<FeedsViewModel>
}