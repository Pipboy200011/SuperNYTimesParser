package com.n.supernytimesparser.application

import com.n.supernytimesparser.base.data.network.BaseResponse
import com.n.supernytimesparser.main.models.data.respose.TimesNewswireResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * RestApi
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
interface SuperNYTimesParserRestApi {

    @GET("svc/news/v3/content/{source}/{section}.json")
    fun getTimesNewswire(
        @Path("source") source: String,
        @Path("section") section: String
    ): Single<Result<BaseResponse<List<TimesNewswireResponse>>>>
}