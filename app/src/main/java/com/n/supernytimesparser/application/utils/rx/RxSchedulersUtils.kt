package com.n.supernytimesparser.application.utils.rx

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * rx utils
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class RxSchedulersUtils : IRxSchedulersUtils {

    override val mainThreadScheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val iOScheduler: Scheduler
        get() = Schedulers.io()

    override fun <T> getIOToMainTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { objectObservable: Observable<T> ->
            objectObservable
                .subscribeOn(iOScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    override fun <T> getIOToMainTransformerFlowable(): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable: Flowable<T> ->
            flowable
                .subscribeOn(iOScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    override fun <T> getIOToMainTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { single: Single<T> ->
            single
                .subscribeOn(iOScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    override val iOToMainTransformerCompletable: CompletableTransformer
        get() = CompletableTransformer { completable: Completable ->
            completable
                .subscribeOn(iOScheduler)
                .observeOn(mainThreadScheduler)
        }
}