package com.n.supernytimesparser.main.domain.repository

import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single

/**
 * interactor to work with news
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
interface IMainRepository {

    /**
     * get list of news
     */
    fun getTimesNewswire(): Single<BaseDomainBean<List<NewswireBean>>>
}