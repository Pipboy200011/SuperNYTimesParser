package com.n.supernytimesparser.main.data.repository

import com.n.supernytimesparser.application.SuperNYTimesParserRestApi
import com.n.supernytimesparser.base.data.network.BaseResponse
import com.n.supernytimesparser.main.domain.repository.IMainRepository
import com.n.supernytimesparser.main.models.data.respose.TimesNewswireResponse
import io.reactivex.Single
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*
import retrofit2.Response

/**
 * Test for [MainRepository]
 * @author Pavel Apanovskii on 24/03/2021.
 */
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    @Mock
    private lateinit var restApi: SuperNYTimesParserRestApi

    private lateinit var mainRepository: IMainRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainRepository = MainRepository(restApi)
    }

    @Test
    fun getTimesNewswire() {

        val response = BaseResponse<List<TimesNewswireResponse>>()
        val result = retrofit2.adapter.rxjava2.Result.response(Response.success(response))

        Mockito.`when`(restApi.getTimesNewswire("all", "all")).thenReturn(Single.just(result))

        mainRepository.getTimesNewswire()

        Mockito.verify(restApi).getTimesNewswire("all", "all")
    }
}