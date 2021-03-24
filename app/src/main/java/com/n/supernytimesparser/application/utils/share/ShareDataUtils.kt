package com.n.supernytimesparser.application.utils.share

import android.content.Context
import android.content.Intent

/**
 * utils to share data
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */

/**
 * share link/url
 */
fun shareDataLikeLink(context: Context, link: String) {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "text/html"
    sharingIntent.putExtra(Intent.EXTRA_TEXT, link)
    context.startActivity(sharingIntent)
}

