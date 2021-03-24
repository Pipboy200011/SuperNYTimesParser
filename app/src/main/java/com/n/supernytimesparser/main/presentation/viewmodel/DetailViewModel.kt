package com.n.supernytimesparser.main.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.n.supernytimesparser.base.presentation.viewmodel.BaseViewModel

/**
 * ViewModel to show detail news content
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class DetailViewModel : BaseViewModel() {

    private var newsUrl: String? = null

    val newsUrlLiveData: MutableLiveData<String> = MutableLiveData()
    val shareNewsUrlLiveData: MutableLiveData<String> = MutableLiveData()

    /**
     * set new url to show content
     */
    fun setNewsUrl(newsUrl: String) {
        this.newsUrl = newsUrl
        newsUrlLiveData.postValue(newsUrl)
    }

    /**
     * share link
     */
    fun shareUrl() {
        if (!newsUrl.isNullOrEmpty()) {
            shareNewsUrlLiveData.postValue(newsUrl)
        }
    }
}