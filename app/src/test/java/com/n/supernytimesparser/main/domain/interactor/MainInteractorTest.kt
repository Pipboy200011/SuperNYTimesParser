package com.n.supernytimesparser.main.domain.interactor

import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.domain.repository.IMainRepository
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Test for [MainInteractor]
 *
 * @author Pavel Apanovskii on 24/03/2021.
 */
@RunWith(MockitoJUnitRunner::class)
class MainInteractorTest {

    @Mock
    private lateinit var mainRepository: IMainRepository

    private lateinit var mainInteractor: IMainInteractor

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainInteractor = MainInteractor(mainRepository)
    }

    @Test
    fun getTimesNewswire() {
        val list = arrayListOf<NewswireBean>()
        list.add(NewswireBean("imageUrl", "title", "newsUrl"))
        list.add(NewswireBean("imageUrl1", "title1", "newsUrl1"))

        val baseDomainBean = BaseDomainBean<List<NewswireBean>>()
        baseDomainBean.successObject = list

        `when`(mainRepository.getTimesNewswire()).thenReturn(Single.just(baseDomainBean))

        mainInteractor.getTimesNewswire()

        verify(mainRepository).getTimesNewswire()
    }
}