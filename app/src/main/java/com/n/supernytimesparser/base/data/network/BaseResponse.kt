package com.n.supernytimesparser.base.data.network

import com.google.gson.annotations.SerializedName

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class BaseResponse<T> {

    @SerializedName("results")
    val result: T? = null

    @SerializedName("fault")
    val error: BaseErrorResponseBody? = null
}