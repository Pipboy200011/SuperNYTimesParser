package com.n.supernytimesparser.base.data.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * @author Pavel Apanovskii on 23/03/2021.
 */
class BaseErrorResponseBody {

    @SerializedName("faultstring")
    var message: String? = null
}