package com.n.supernytimesparser.main.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import com.n.supernytimesparser.application.utils.rx.RxSchedulersTestUtils
import com.n.supernytimesparser.base.domain.BaseDomainBean
import com.n.supernytimesparser.main.domain.interactor.IMainInteractor
import com.n.supernytimesparser.main.models.domain.NewswireBean
import io.reactivex.Single
import org.junit.*
import org.junit.rules.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Test for [MainViewModel]
 * @author Pavel Apanovskii on 24/03/2021.
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainInteractor: IMainInteractor

    @Mock
    private lateinit var resourceManager: ResourceManager

    @Mock
    private lateinit var newsObserver: Observer<List<NewswireBean>>

    @Mock
    private lateinit var showShimmersObserver: Observer<Boolean>

    @Mock
    private lateinit var showDetailScreenObserver: Observer<String>

    @Mock
    private lateinit var showErrorObserver: Observer<String>

    @Mock
    private lateinit var hideSearchObserver: Observer<Boolean>

    private lateinit var mainViewModel: MainViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val rxSchedulersTestUtils = RxSchedulersTestUtils()
        mainViewModel = MainViewModel(mainInteractor, rxSchedulersTestUtils, resourceManager)
        mainViewModel.newsLiveData().observeForever(newsObserver)
        mainViewModel.showShimmersLiveData().observeForever(showShimmersObserver)
        mainViewModel.showDetailScreenLiveData().observeForever(showDetailScreenObserver)
        mainViewModel.showErrorLiveData().observeForever(showErrorObserver)
        mainViewModel.hideSearchLiveData().observeForever(hideSearchObserver)
    }

    @Test
    fun showContent() {
        val list = arrayListOf<NewswireBean>()
        list.add(NewswireBean("imageUrl", "title", "newsUrl"))
        list.add(NewswireBean("imageUrl1", "title1", "newsUrl1"))

        val baseDomainBean = BaseDomainBean<List<NewswireBean>>()
        baseDomainBean.successObject = list

        `when`(mainInteractor.getTimesNewswire()).thenReturn(Single.just(baseDomainBean))

        mainViewModel.init()

        verify(mainInteractor).getTimesNewswire()
        verify(showShimmersObserver).onChanged(true)
        verify(hideSearchObserver).onChanged(true)
        verify(newsObserver).onChanged(list)
    }

    @Test
    fun selectNews() {
        val list = arrayListOf<NewswireBean>()
        list.add(NewswireBean("imageUrl", "title", "newsUrl"))
        list.add(NewswireBean("imageUrl1", "title1", "newsUrl1"))

        val baseDomainBean = BaseDomainBean<List<NewswireBean>>()
        baseDomainBean.successObject = list

        `when`(mainInteractor.getTimesNewswire()).thenReturn(Single.just(baseDomainBean))

        mainViewModel.init()
        mainViewModel.selectNews(0)

        verify(showDetailScreenObserver).onChanged("newsUrl")
    }

    @Test
    fun filter() {
        val list = arrayListOf<NewswireBean>()
        list.add(NewswireBean("imageUrl", "title", "newsUrl"))
        list.add(NewswireBean("imageUrl1", "title1", "newsUrl1"))

        val listFilter = arrayListOf<NewswireBean>()
        listFilter.add(NewswireBean("imageUrl1", "title1", "newsUrl1"))

        val baseDomainBean = BaseDomainBean<List<NewswireBean>>()
        baseDomainBean.successObject = list

        `when`(mainInteractor.getTimesNewswire()).thenReturn(Single.just(baseDomainBean))

        mainViewModel.init()
        mainViewModel.filter("title1")

        verify(newsObserver).onChanged(listFilter)
    }
}