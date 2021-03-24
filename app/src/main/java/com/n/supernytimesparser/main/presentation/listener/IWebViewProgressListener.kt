package com.n.supernytimesparser.main.presentation.listener

/**
 * listener for custom WebView
 * @author Pavel Apanovskii on 24/03/2021.
 */
interface IWebViewProgressListener {

    /**
     * start load page
     */
    fun onPageStarted()

    /**
     * finis load page
     */
    fun onPageFinished()
}