package com.n.supernytimesparser.main.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.n.supernytimesparser.R
import com.n.supernytimesparser.application.SuperNYTimesParserApplication
import com.n.supernytimesparser.application.utils.resource.ResourceManager
import com.n.supernytimesparser.application.utils.rx.IRxSchedulersUtils
import com.n.supernytimesparser.base.presentation.listener.IAdapterItemClickListener
import com.n.supernytimesparser.base.presentation.ui.BaseActivity
import com.n.supernytimesparser.base.presentation.viewmodel.ViewModelProviderFactory
import com.n.supernytimesparser.databinding.MainActivityBinding
import com.n.supernytimesparser.main.di.MainInjector
import com.n.supernytimesparser.main.domain.interactor.IMainInteractor
import com.n.supernytimesparser.main.models.domain.NewswireBean
import com.n.supernytimesparser.main.presentation.adapter.NewswireElementsAdapter
import com.n.supernytimesparser.main.presentation.viewmodel.MainViewModel

/**
 * Activity to show news list
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    private lateinit var mainInteractor: IMainInteractor
    private lateinit var rxSchedulersUtils: IRxSchedulersUtils
    private lateinit var resourceManager: ResourceManager

    private lateinit var mainViewModel: MainViewModel

    private lateinit var newswireAdapter: NewswireElementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDependencies()
        initViewModel()
        initObservers()
        initAdapter()
        initSearch()

        mainViewModel.init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            mainViewModel.init()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initDependencies() {
        mainInteractor = MainInjector.mainComponent.mainInteractor
        rxSchedulersUtils = SuperNYTimesParserApplication.applicationComponent.rxSchedulersUtils
        resourceManager = SuperNYTimesParserApplication.applicationComponent.resourceManager
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(
            this, ViewModelProviderFactory(
                {
                    MainViewModel(
                        mainInteractor,
                        rxSchedulersUtils,
                        resourceManager
                    )
                },
                null
            )
        )
            .get(MainViewModel::class.java)
    }

    private fun initObservers() {
        mainViewModel.newsLiveData().observe(this, { news -> updateNews(news) })
        mainViewModel.showShimmersLiveData().observe(this, { show -> updateShimmersVisibilityState(show) })
        mainViewModel.showDetailScreenLiveData().observe(this, { newsUrl -> showDetailScreen(newsUrl) })
        mainViewModel.showErrorLiveData().observe(this, { text -> showErrorText(text) })
        mainViewModel.hideSearchLiveData().observe(this, { hide -> updateActiveSearchVisibilityState(hide) })
    }

    private fun initAdapter() {
        newswireAdapter = NewswireElementsAdapter(object : IAdapterItemClickListener {
            override fun onClick(position: Int) {
                mainViewModel.selectNews(position)
            }
        })
        binding.newsContainer.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newswireAdapter
        }
    }

    private fun initSearch() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.filter(newText)
                return true
            }
        })
    }

    private fun updateNews(news: List<NewswireBean>) {
        newswireAdapter.setData(news)
    }

    private fun updateShimmersVisibilityState(show: Boolean) {
        when {
            show -> {
                binding.shimmersContainer.shimmersContainer.visibility = View.VISIBLE
                binding.newsContainer.visibility = View.INVISIBLE
            }
            !show -> {
                binding.shimmersContainer.shimmersContainer.visibility = View.GONE
                binding.newsContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun showDetailScreen(newsUrl: String) {
        startActivity(DetailActivity.newIntent(this, newsUrl))
    }

    private fun showErrorText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun updateActiveSearchVisibilityState(hide: Boolean) {
        binding.searchView.onActionViewCollapsed()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}