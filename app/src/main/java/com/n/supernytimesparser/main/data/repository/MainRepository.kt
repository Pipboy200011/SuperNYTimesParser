package com.n.supernytimesparser.main.data.repository

import com.n.supernytimesparser.application.SuperNYTimesParserRestApi
import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.data.converter.TimesNewswireResponseConverter
import com.n.supernytimesparser.main.domain.repository.IMainRepository
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single

/**
 * main repository
 * @author Pavel Apanovskii on 23/03/2021.
 */
class MainRepository(private val restApi: SuperNYTimesParserRestApi) : IMainRepository {

    override fun getTimesNewswire(): Single<BaseDomainBean<List<NewswireBean>>> {
        return restApi.getTimesNewswire("all", "all")
            .map { result ->
                TimesNewswireResponseConverter().convert(result)
            }
    }
}