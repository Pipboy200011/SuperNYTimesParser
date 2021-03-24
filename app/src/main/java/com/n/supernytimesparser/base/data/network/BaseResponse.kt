package com.n.supernytimesparser.base.data.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class BaseResponse<T1> {

    @SerializedName("results")
    @Expose
    val result: T1? = null

    @SerializedName("fault")
    @Expose
    val error: BaseErrorResponseBody? = null
}