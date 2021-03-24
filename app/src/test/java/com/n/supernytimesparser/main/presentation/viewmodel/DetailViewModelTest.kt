package com.n.supernytimesparser.main.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.*
import org.junit.rules.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Test for [DetailViewModel]
 * @author Pavel Apanovskii on 24/03/2021.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsUrlObserver: Observer<String>

    @Mock
    private lateinit var shareNewsUrlObserver: Observer<String>

    private lateinit var detailViewModel: DetailViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        detailViewModel = DetailViewModel()
        detailViewModel.newsUrlLiveData().observeForever(newsUrlObserver)
        detailViewModel.shareNewsUrlLiveData().observeForever(shareNewsUrlObserver)
    }

    @Test
    fun showContent() {
        detailViewModel.setNewsUrl("superUrl")

        verify(newsUrlObserver).onChanged("superUrl")
    }

    @Test
    fun shareUrl() {
        detailViewModel.setNewsUrl("superUrl")
        detailViewModel.shareUrl()

        verify(shareNewsUrlObserver).onChanged("superUrl")
    }
}