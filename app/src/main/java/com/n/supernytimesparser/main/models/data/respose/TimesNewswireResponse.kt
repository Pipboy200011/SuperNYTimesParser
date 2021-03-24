package com.n.supernytimesparser.main.models.data.respose

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * news list model
 * @author Pavel Apanovskii on 23/03/2021.
 */
class TimesNewswireResponse {

    @SerializedName("slug_name")
    @Expose
    var slugName: String? = null

    @SerializedName("section")
    @Expose
    var section: String? = null

    @SerializedName("subsection")
    @Expose
    var subsection: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("abstract")
    @Expose
    var _abstract: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("byline")
    @Expose
    var byline: String? = null

    @SerializedName("thumbnail_standard")
    @Expose
    var thumbnailStandard: String? = null

    @SerializedName("item_type")
    @Expose
    var itemType: String? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("updated_date")
    @Expose
    var updatedDate: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null

    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null

    @SerializedName("first_published_date")
    @Expose
    var firstPublishedDate: String? = null

    @SerializedName("material_type_facet")
    @Expose
    var materialTypeFacet: String? = null

    @SerializedName("kicker")
    @Expose
    var kicker: String? = null

    @SerializedName("subheadline")
    @Expose
    var subheadline: String? = null

    @SerializedName("des_facet")
    @Expose
    var desFacet: Any? = null

    @SerializedName("org_facet")
    @Expose
    var orgFacet: Any? = null

    @SerializedName("per_facet")
    @Expose
    var perFacet: Any? = null

    @SerializedName("geo_facet")
    @Expose
    var geoFacet: Any? = null

    @SerializedName("related_urls")
    @Expose
    var relatedUrls: Any? = null

    @SerializedName("multimedia")
    @Expose
    var multimedia: List<TimesNewswireMultimediumResponse>? = null
}