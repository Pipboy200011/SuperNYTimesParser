package com.n.supernytimesparser.application.utils.rx

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer

/**
 * utils fot RX
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
interface IRxSchedulersUtils {

    val mainThreadScheduler: Scheduler
    val iOScheduler: Scheduler

    fun <T> getIOToMainTransformer(): ObservableTransformer<T, T>
    fun <T> getIOToMainTransformerFlowable(): FlowableTransformer<T, T>
    fun <T> getIOToMainTransformerSingle(): SingleTransformer<T, T>

    val iOToMainTransformerCompletable: CompletableTransformer
}