package com.n.supernytimesparser.application

import android.app.Application
import com.n.supernytimesparser.application.di.ApplicationComponent
import com.n.supernytimesparser.application.di.DaggerApplicationComponent
import com.n.supernytimesparser.application.di.module.NetworkModule
import com.n.supernytimesparser.application.di.module.RxModule

/**
 * project application
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class SuperNYTimesParserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .rxModule(RxModule())
            .build()
    }

    companion object {

        lateinit var instance: SuperNYTimesParserApplication
        lateinit var applicationComponent: ApplicationComponent
    }
}