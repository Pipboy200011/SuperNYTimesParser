package com.n.supernytimesparser.main.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.n.supernytimesparser.R
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import com.n.supernytimesparser.application.utils.rx.IRxSchedulersUtils
import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.base.presentation.viewmodel.BaseViewModel
import com.n.supernytimesparser.main.domain.interactor.IMainInteractor
import com.n.supernytimesparser.main.models.domain.NewswireBean

const val TAG = "MainViewModel"

/**
 * ViewModel for news list
 * @author Pavel Apanovskii on 23/03/2021.
 */
class MainViewModel(
    private val mainInteractor: IMainInteractor,
    private val rxSchedulersUtils: IRxSchedulersUtils,
    private val resourceManager: ResourceManager
) :
    BaseViewModel() {

    private var newsList: List<NewswireBean> = arrayListOf()

    private val newsLiveData: MutableLiveData<List<NewswireBean>> = MutableLiveData()
    private val showShimmersLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val showDetailScreenLiveData: MutableLiveData<String> = MutableLiveData()
    private val showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    private val hideSearchLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun newsLiveData(): LiveData<List<NewswireBean>> = newsLiveData
    fun showShimmersLiveData(): LiveData<Boolean> = showShimmersLiveData
    fun showDetailScreenLiveData(): LiveData<String> = showDetailScreenLiveData
    fun showErrorLiveData(): LiveData<String> = showErrorLiveData
    fun hideSearchLiveData(): LiveData<Boolean> = hideSearchLiveData

    fun init() {
        getTimesNewswire()
    }

    fun selectNews(position: Int) {
        newsLiveData.value?.get(position)?.let {
            showDetailScreenLiveData.postValue(it.newsLink)
        }
    }

    fun filter(filterText: String?) {
        val newsWithFilter = newsList.filter { bean -> bean.title?.contains(filterText.toString(), true) == true }
        newsLiveData.postValue(newsWithFilter)
    }

    private fun getTimesNewswire() {
        rxCompositeDisposable.add(
            mainInteractor.getTimesNewswire()
                .compose(rxSchedulersUtils.getIOToMainTransformerSingle())
                .doOnSubscribe { showShimmers() }
                .subscribe({ result -> checkResult(result) }, { error -> checkError(error) })
        )
    }

    private fun showShimmers() {
        showShimmersLiveData.postValue(true)
        hideSearchLiveData.postValue(true)
    }

    private fun checkResult(result: BaseDomainBean<List<NewswireBean>>) {
        if (!result.successObject.isNullOrEmpty()) {
            showShimmersLiveData.postValue(false)
            newsList = result.successObject!!
            newsLiveData.postValue(result.successObject)
        } else {
            Log.e(TAG, "getTimesNewswire error: " + result.errorObject)
            showErrorLiveData.postValue(resourceManager.getString(R.string.standard_error_message))
        }
    }

    private fun checkError(error: Throwable) {
        Log.e(TAG, "getTimesNewswire error:", error)
        showErrorLiveData.postValue(resourceManager.getString(R.string.standard_error_message))
    }
}