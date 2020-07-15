package com.clean.app.views.feeds

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clean.app.BuildConfig
import com.clean.app.R
import com.clean.app.common.Injector
import com.clean.app.data.entity.Feeds
import com.clean.app.data.entity.Row
import com.clean.app.utils.ConnectionUtils
import kotlinx.android.synthetic.main.activity_feeds.*
import kotlinx.android.synthetic.main.custom_toolbar.*

/**
 * Created by rohit.anvekar on 14/7/20.
 */
class FeedsActivity : AppCompatActivity() {

    private lateinit var feedsAdapter: FeedsAdapter
    private var rowList = mutableListOf<Row>()

    private val viewModel: FeedsViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().feedsViewModelFactory()
        ).get(FeedsViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        initFeedViews()
        loadAndUpdateFeeds()
    }

    /**
     * loadAndUpdateFeeds() to perform different operation to display feeds
     */
    private fun loadAndUpdateFeeds(){
        loadFeeds()
        observeFeedData()
        observeErrors()
        observeProgress()
    }

    /**
     * loadArticles() func to load
     */
    private fun loadFeeds() {
        if (ConnectionUtils.isNetworkAvailable()) {
            viewModel.getFeeds(BuildConfig.FEED_URL)
        } else {
            showToast(getString(R.string.network_not_available))
        }
    }

    /**
     * initFeedViews() func to initialize the feed views to display list item.
     */
    private fun initFeedViews() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        feedsAdapter =
            FeedsAdapter(this,rowList, object : FeedsAdapter.OnListItemInteractionListener {
                override fun onItemClicked(item: Feeds) {
                }

            })
        recycler_view.adapter = feedsAdapter
        swipeRefreshView.setOnRefreshListener {
            loadFeeds()
        }
    }

    /**
     * observeProgress() func to observe the progress
     */
    private fun observeProgress() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        })
    }

    /**
     * observeFeedData() func to observe the feed data
     */
    private fun observeFeedData() {
        viewModel.feeds.observe(this, Observer {
            appTitle.text = it.title
            rowList.clear()
            rowList.addAll(it.row)
            feedsAdapter.notifyDataSetChanged()
            swipeRefreshView.isRefreshing = false
        })
    }

    /**
     * observeErrors() func to observe the feed error
     */
    private fun observeErrors() {
        viewModel.error.observe(this, Observer { errorMesage ->
            showToast(errorMesage)
            swipeRefreshView.isRefreshing = false
        })
    }

    /**
     * showToast() func to show message
     */
    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
