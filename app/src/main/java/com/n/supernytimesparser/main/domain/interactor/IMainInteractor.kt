package com.n.supernytimesparser.main.domain.interactor

import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single

/**
 * interactor to work with news
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
interface IMainInteractor {

    /**
     * get list of news
     */
    fun getTimesNewswire(): Single<BaseDomainBean<List<NewswireBean>>>
}