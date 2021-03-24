package com.n.supernytimesparser.main.di

import com.n.supernytimesparser.main.domain.interactor.IMainInteractor
import dagger.Subcomponent

/**
 * Main component
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {

    val mainInteractor: IMainInteractor

    @Subcomponent.Builder
    interface Builder {

        fun mainModule(mainModule: MainModule): Builder
        fun build(): MainComponent
    }
}