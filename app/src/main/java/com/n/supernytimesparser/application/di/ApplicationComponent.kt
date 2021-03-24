package com.n.supernytimesparser.application.di

import com.n.supernytimesparser.application.di.module.NetworkModule
import com.n.supernytimesparser.application.di.module.RxModule
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import com.n.supernytimesparser.application.utils.rx.IRxSchedulersUtils
import com.n.supernytimesparser.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Main Application Component
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Component(modules = [ApplicationModule::class, NetworkModule::class, RxModule::class])
@Singleton
interface ApplicationComponent {

    fun plusMainComponent(): MainComponent.Builder

    val rxSchedulersUtils: IRxSchedulersUtils
    val resourceManager: ResourceManager
}