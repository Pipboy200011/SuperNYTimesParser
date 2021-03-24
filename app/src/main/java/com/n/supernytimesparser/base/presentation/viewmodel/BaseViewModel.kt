package com.n.supernytimesparser.base.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
open class BaseViewModel : ViewModel() {

    val rxCompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        rxCompositeDisposable.dispose()
    }
}