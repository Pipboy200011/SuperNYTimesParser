package com.n.supernytimesparser.main.data.converter

import com.n.supernytimesparser.base.data.network.BaseResponse
import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.base.domain.ErrorMessage
import com.n.supernytimesparser.main.models.data.respose.TimesNewswireResponse
import com.n.supernytimesparser.main.models.domain.NewswireBean
import retrofit2.adapter.rxjava2.Result

/**
 * converter for news list
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class TimesNewswireResponseConverter {

    fun convert(result: Result<BaseResponse<List<TimesNewswireResponse>>>): BaseDomainBean<List<NewswireBean>> {

        val baseDomainBean = BaseDomainBean<List<NewswireBean>>()
        val newsBeanList = arrayListOf<NewswireBean>()

        val originalNews = result.response()?.body()?.result

        when {
            !originalNews.isNullOrEmpty() -> {
                originalNews.forEach { element ->
                    newsBeanList.add(convert(element))
                }
                baseDomainBean.successObject = newsBeanList
            }
            result.isError -> {
                baseDomainBean.errorObject = ErrorMessage(result.error()?.message)
            }
            !result.response()?.errorBody()?.string().isNullOrEmpty() -> {
                baseDomainBean.errorObject = ErrorMessage(result.response()?.errorBody()?.string())
            }
            else -> {
                baseDomainBean.errorObject = ErrorMessage(result.response()?.body()?.error?.message)
            }
        }
        return baseDomainBean
    }

    private fun convert(result: TimesNewswireResponse): NewswireBean {
        return NewswireBean(result.thumbnailStandard, result.title, result.url)
    }
}