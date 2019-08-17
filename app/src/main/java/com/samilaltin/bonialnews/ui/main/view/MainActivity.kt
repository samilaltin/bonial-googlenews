package com.samilaltin.bonialnews.ui.main.view

import android.os.Bundle
import android.view.ViewGroup
import com.samilaltin.bonialnews.R
import com.samilaltin.bonialnews.data.model.response.HomeResponse
import com.samilaltin.bonialnews.ui.base.view.BaseBottomUpActivity
import com.samilaltin.bonialnews.ui.main.interactor.MainInteractor
import com.samilaltin.bonialnews.ui.main.presenter.MainPresenterImp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.samilaltin.bonialnews.ui.main.adapter.ArticlesRecyclerAdapter
import android.content.res.Configuration
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samilaltin.bonialnews.data.model.response.Articles
import com.samilaltin.bonialnews.database.DatabaseClient
import com.samilaltin.bonialnews.database.DatabaseInitializer
import com.samilaltin.bonialnews.database.DatabaseGetArticles
import com.samilaltin.bonialnews.ui.main.handler.ArticleHandlers
import com.samilaltin.bonialnews.ui.main.listener.EndlessRecyclerOnScrollListener
import com.samilaltin.bonialnews.utility.CommonUtil
import com.samilaltin.bonialnews.utility.transition.ActivityTransition

/**
 * Created by samilaltin on 09/08/2019
 */
class MainActivity : BaseBottomUpActivity(), MainView, ArticleHandlers {

    @Inject
    lateinit var mMainPresenter: MainPresenterImp<MainView, MainInteractor>
    private var articlesRecyclerAdapter: ArticlesRecyclerAdapter? = null
    private lateinit var manager: StaggeredGridLayoutManager
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var articlePage = 1

    override fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup {
        setContentView(R.layout.activity_main)
        mMainPresenter.onAttach(this)
        checkConfiguration()
        return rootMainActivity as ViewGroup
    }

    override fun bindView() {
        setToolbar()
        if (CommonUtil.isOnline(this)) {
            mMainPresenter.getArticles(this, "en", articlePage)
        } else {
            DatabaseGetArticles.getArticleDBAsync(DatabaseClient.getInstance(this@MainActivity).appDatabase, this)
        }
    }

    fun setOfflineAdapter(
        articles: List<Articles>, size: Int
    ) {
        if (size < 1) {
            showSnackbar(resources.getString(R.string.no_internet_connection))
        } else {
            articlesRV.layoutManager = manager
            articlesRecyclerAdapter = ArticlesRecyclerAdapter(articles, this)
            articlesRV.adapter = articlesRecyclerAdapter
        }
    }

    override fun openHome(homeResponse: HomeResponse) {
        articlesRV.setHasFixedSize(true)
        articlesRV.setItemViewCacheSize(20)
        articlesRV.isDrawingCacheEnabled = true
        /** Based on EndlessRecyclerOnScrollListener -> GitHub : https://gist.github.com/pratikbutani/dc6b963aa12200b3ad88aecd0d103872 */
        articlesRV.addOnScrollListener(object : EndlessRecyclerOnScrollListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                isLoading = true
                if (!isLastPage) {
                    if (articlesRV.adapter != null) {
                        mMainPresenter.getArticles(this@MainActivity, "en", articlePage)
                    }
                }
            }
        })
        articlesRV.layoutManager = manager
        setAdapter(homeResponse)
    }

    override fun fail() {
    }

    override fun terminate() {
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        checkConfiguration()
        super.onConfigurationChanged(newConfig)
    }

    override fun onArticleClick(url: String) {
        if (CommonUtil.isOnline(this)) {
            if (url.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("url", url)
                ActivityTransition.Builder(this, ArticleDetailWebView::class.java).setBundle(bundle).build().start()
            } else {
                showSnackbar(resources.getString(R.string.content_resource_not_found))
            }
        } else {
            showSnackbar(resources.getString(R.string.no_internet_connection))
        }
    }

    private fun setToolbar() {
        bonialToolbar.setTitle("bonial.com News")
        bonialToolbar.setSetBgColor(ContextCompat.getColor(baseContext, R.color.bonial_bright_red))
    }

    private fun setAdapter(homeResponse: HomeResponse) {
        if (articlePage == 1) {
            initializeArticleDB(homeResponse, true)
            articlesRecyclerAdapter = ArticlesRecyclerAdapter(homeResponse.articles, this)
            articlesRV.adapter = articlesRecyclerAdapter
        } else {
            articlesRecyclerAdapter?.addAll(homeResponse.articles)
            initializeArticleDB(homeResponse, false)
        }
        articlePage++
        isLastPage = articlesRecyclerAdapter?.itemCount == homeResponse.totalResults
        isLoading = false
    }

    override fun checkConfiguration() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            articlesRV.layoutManager = manager
        } else {
            manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            articlesRV.layoutManager = manager
        }
    }

    private fun initializeArticleDB(homeResponse: HomeResponse, deleteDB: Boolean) {
        DatabaseInitializer.populateArticleDBAsync(
            DatabaseClient.getInstance(this@MainActivity).appDatabase,
            homeResponse.articles, deleteDB
        )
    }
}
