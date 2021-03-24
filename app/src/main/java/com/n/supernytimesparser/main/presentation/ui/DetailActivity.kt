package com.n.supernytimesparser.main.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.n.supernytimesparser.R
import com.n.supernytimesparser.application.utils.share.shareDataLikeLink
import com.n.supernytimesparser.base.presentation.ui.BaseActivity
import com.n.supernytimesparser.base.presentation.viewmodel.ViewModelProviderFactory
import com.n.supernytimesparser.databinding.DetailActivityBinding
import com.n.supernytimesparser.main.presentation.listener.IWebViewProgressListener
import com.n.supernytimesparser.main.presentation.viewmodel.DetailViewModel

const val NEWS_URL = "NEWS_URL"

/**
 * Activity to show detail news content
 *
 * @author Pavel Apanovskii on 23/03/2021.
 */
class DetailActivity : BaseActivity() {

    private lateinit var binding: DetailActivityBinding

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObservers()
        setToolbarParameters()

        intent?.let { it ->
            it.getStringExtra(NEWS_URL)?.let {
                detailViewModel.setNewsUrl(it)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        intent?.let { it ->
            it.getStringExtra(NEWS_URL)?.let {
                detailViewModel.setNewsUrl(it)
            }
        }
        super.onNewIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.share) {
            detailViewModel.shareUrl()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProvider(
            this, ViewModelProviderFactory({ DetailViewModel() }, null)
        )
            .get(DetailViewModel::class.java)
    }

    private fun initObservers() {
        detailViewModel.newsUrlLiveData().observe(this, { newsUrl -> updateContent(newsUrl) })
        detailViewModel.shareNewsUrlLiveData().observe(this, { newsUrl -> shareNewsUrl(newsUrl) })
    }

    private fun setToolbarParameters() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun updateContent(newsUrl: String) {
        binding.webview.loadUrl(newsUrl)
        binding.webview.webViewClient = DetailWebViewClient(object : IWebViewProgressListener {
            override fun onPageStarted() {
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished() {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun shareNewsUrl(newsUrl: String) {
        shareDataLikeLink(this, newsUrl)
    }

    companion object {

        fun newIntent(context: Context, newsUrl: String): Intent {
            return Intent(context, DetailActivity::class.java).putExtra(NEWS_URL, newsUrl)
        }
    }
}