package com.n.supernytimesparser.main.models.data.respose

import com.google.gson.annotations.SerializedName

/**
 * news list multimedia response model
 * @author Pavel Apanovskii on 23/03/2021.
 */
class TimesNewswireMultimediumResponse {

    @SerializedName("url")
    var url: String? = null

    @SerializedName("format")
    var format: String? = null

    @SerializedName("height")
    var height: Int? = null

    @SerializedName("width")
    var width: Int? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("subtype")
    var subtype: String? = null

    @SerializedName("caption")
    var caption: String? = null

    @SerializedName("copyright")
    var copyright: String? = null
}