package com.n.supernytimesparser.main.models.data.respose

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * news list multimedia response model
 * @author Pavel Apanovskii on 23/03/2021.
 */
class TimesNewswireMultimediumResponse {

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("subtype")
    @Expose
    var subtype: String? = null

    @SerializedName("caption")
    @Expose
    var caption: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
}