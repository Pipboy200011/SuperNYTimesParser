package com.n.supernytimesparser.main.domain.interactor

import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.domain.repository.IMainRepository
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class MainInteractor(private val repository: IMainRepository) : IMainInteractor {

    override fun getTimesNewswire(): Single<BaseDomainBean<List<NewswireBean>>> {
        return repository.getTimesNewswire()
    }
}