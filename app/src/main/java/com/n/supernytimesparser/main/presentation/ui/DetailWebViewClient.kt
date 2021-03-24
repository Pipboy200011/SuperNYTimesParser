package com.n.supernytimesparser.main.presentation.ui

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.n.supernytimesparser.main.presentation.listener.IWebViewProgressListener

/**
 * Custom WebViewClient with progress listener
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */
class DetailWebViewClient(private val listener: IWebViewProgressListener) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        listener.onPageStarted()
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        listener.onPageFinished()
        super.onPageFinished(view, url)
    }
}