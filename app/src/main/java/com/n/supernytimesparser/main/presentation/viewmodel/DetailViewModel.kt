package com.n.supernytimesparser.main.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.n.supernytimesparser.base.presentation.viewmodel.BaseViewModel

/**
 * ViewModel to show detail news content
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class DetailViewModel : BaseViewModel() {

    private var newsUrl: String? = null

    private val newsUrlLiveData: MutableLiveData<String> = MutableLiveData()
    private val shareNewsUrlLiveData: MutableLiveData<String> = MutableLiveData()

    fun newsUrlLiveData(): LiveData<String> = newsUrlLiveData
    fun shareNewsUrlLiveData(): LiveData<String> = shareNewsUrlLiveData

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