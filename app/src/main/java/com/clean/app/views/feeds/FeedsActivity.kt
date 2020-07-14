package com.clean.app.views.feeds

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        checkNetworkAvailability()
        initFeedViews()
        loadArticles()
        observeFeedData()
        observeErrors()
        observeProgress()
    }

    private fun loadArticles() {
        if (ConnectionUtils.isNetworkAvailable()) {
            viewModel.getFeeds(BuildConfig.FEED_URL)
        } else {
            showToast(getString(R.string.network_not_available))
        }
    }

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
            loadArticles()
        }
    }

    private fun observeProgress() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        })
    }

    /**
     * observeFeedData() func observe the feed data using
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

    private fun observeErrors() {
        viewModel.error.observe(this, Observer { errorMesage ->
            showToast(errorMesage)
            swipeRefreshView.isRefreshing = false
        })
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Function : checkNetworkAvailability for checking
     * network availability
     */
    fun checkNetworkAvailability() {
        // get ConnectivityManager
        val cm:ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder: NetworkRequest.Builder = NetworkRequest.Builder()
            cm.registerNetworkCallback(

                builder.build(),
                object : ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        CoroutineScope(Dispatchers.Main).launch {
                            ConnectionUtils.isNetworkAvailableOnAndroidP = true
                        }
                    }

                    override fun onLost(network: Network) {
                        CoroutineScope(Dispatchers.Main).launch {
                            ConnectionUtils.isNetworkAvailableOnAndroidP = false
                        }
                    }
                }
            )
        }
    }

}
