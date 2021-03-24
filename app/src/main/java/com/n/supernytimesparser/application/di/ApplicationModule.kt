package com.n.supernytimesparser.application.di

import android.content.Context
import com.n.supernytimesparser.application.SuperNYTimesParserApplication
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Main Application Module
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return SuperNYTimesParserApplication.instance.applicationContext
    }

    @Singleton
    @Provides
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager(context)
    }
}