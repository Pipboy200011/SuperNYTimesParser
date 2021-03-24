package com.n.supernytimesparser.main.di

import com.n.supernytimesparser.application.SuperNYTimesParserApplication

/**
 * Main injector
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */

object MainInjector {

    val mainComponent: MainComponent by lazy {
        SuperNYTimesParserApplication.applicationComponent
            .plusMainComponent()
            .mainModule(MainModule())
            .build()
    }
}