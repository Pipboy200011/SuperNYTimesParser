package com.n.supernytimesparser.main.models.data.respose

import com.google.gson.annotations.SerializedName

/**
 * news list model
 * @author Pavel Apanovskii on 23/03/2021.
 */
class TimesNewswireResponse {

    @SerializedName("slug_name")
    var slugName: String? = null

    @SerializedName("section")
    var section: String? = null

    @SerializedName("subsection")
    var subsection: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("abstract")
    var _abstract: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("byline")
    var byline: String? = null

    @SerializedName("thumbnail_standard")
    var thumbnailStandard: String? = null

    @SerializedName("item_type")
    var itemType: String? = null

    @SerializedName("source")
    var source: String? = null

    @SerializedName("updated_date")
    var updatedDate: String? = null

    @SerializedName("created_date")
    var createdDate: String? = null

    @SerializedName("published_date")
    var publishedDate: String? = null

    @SerializedName("first_published_date")
    var firstPublishedDate: String? = null

    @SerializedName("material_type_facet")
    var materialTypeFacet: String? = null

    @SerializedName("kicker")
    var kicker: String? = null

    @SerializedName("subheadline")
    var subheadline: String? = null

    @SerializedName("des_facet")
    var desFacet: Any? = null

    @SerializedName("org_facet")
    var orgFacet: Any? = null

    @SerializedName("per_facet")
    var perFacet: Any? = null

    @SerializedName("geo_facet")
    var geoFacet: Any? = null

    @SerializedName("related_urls")
    var relatedUrls: Any? = null

    @SerializedName("multimedia")
    var multimedia: List<TimesNewswireMultimediumResponse>? = null
}