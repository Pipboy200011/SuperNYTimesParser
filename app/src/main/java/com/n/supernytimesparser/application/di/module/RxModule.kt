package com.n.supernytimesparser.application.di.module

import com.n.supernytimesparser.application.utils.rx.IRxSchedulersUtils
import com.n.supernytimesparser.application.utils.rx.RxSchedulersUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * RX utils module
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Module
class RxModule {

    @Provides
    @Singleton
    fun provideRxSchedulersUtils(): IRxSchedulersUtils {
        return RxSchedulersUtils()
    }
}