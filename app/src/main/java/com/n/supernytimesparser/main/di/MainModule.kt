package com.n.supernytimesparser.main.di

import com.n.supernytimesparser.application.SuperNYTimesParserRestApi
import com.n.supernytimesparser.main.data.repository.MainRepository
import com.n.supernytimesparser.main.domain.interactor.IMainInteractor
import com.n.supernytimesparser.main.domain.interactor.MainInteractor
import com.n.supernytimesparser.main.domain.repository.IMainRepository
import dagger.Module
import dagger.Provides

/**
 * Main module
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Module
class MainModule {

    @Provides
    @MainScope
    fun provideMainRepository(restApi: SuperNYTimesParserRestApi): IMainRepository {
        return MainRepository(restApi)
    }

    @Provides
    @MainScope
    fun provideMainInteractor(repository: IMainRepository): IMainInteractor {
        return MainInteractor(repository)
    }
}