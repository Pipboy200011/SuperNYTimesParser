package com.n.supernytimesparser.main.models.domain

/**
 * @param thumbnail image URL
 * @param title title
 * @param newsLink news URL
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */
data class NewswireBean(
    var thumbnail: String?,
    var title: String?,
    var newsLink: String?
)