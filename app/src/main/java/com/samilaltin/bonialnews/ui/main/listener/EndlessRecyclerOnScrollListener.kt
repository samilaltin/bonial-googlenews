package com.samilaltin.bonialnews.ui.main.listener

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Created by samilaltin on 15/08/2019
 */
abstract class EndlessRecyclerOnScrollListener(layoutManager: StaggeredGridLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    private var mLayoutManager: RecyclerView.LayoutManager? = layoutManager

    init {
        visibleThreshold *= layoutManager.spanCount
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition: Int
        val totalItemCount = mLayoutManager!!.itemCount
        val lastVisibleItemPositions = (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
        lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount && view.adapter!!.itemCount > visibleThreshold) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}