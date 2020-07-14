package com.clean.app.common.modules

import android.content.Context
import com.clean.app.FeedsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rohit.anvekar on 14/7/20.
 */
@Module
class ApplicationModule(private val application: FeedsApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

}