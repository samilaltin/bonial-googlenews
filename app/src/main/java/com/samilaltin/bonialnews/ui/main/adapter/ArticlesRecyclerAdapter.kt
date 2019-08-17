package com.samilaltin.bonialnews.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samilaltin.bonialnews.R
import com.samilaltin.bonialnews.data.model.response.Articles
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samilaltin.bonialnews.databinding.ListitemArticleBinding
import com.samilaltin.bonialnews.databinding.ListitemArticleHeaderBinding
import com.samilaltin.bonialnews.ui.main.handler.ArticleHandlers


/**
 * Created by samilaltin on 12/08/2019
 */
class ArticlesRecyclerAdapter(
    articles: List<Articles>,
    private val articleHandlers: ArticleHandlers
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var headerBinding: ListitemArticleHeaderBinding
    private lateinit var articleBinding: ListitemArticleBinding
    private val listOfArticles = ArrayList(articles)

    private var context: Context? = null
    private lateinit var recyclerView: RecyclerView

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

    fun addAll(items: List<Articles>?) {
        if (items == null) {
            throw IllegalArgumentException("Cannot add `null` items to the Recycler adapter")
        }
        this.listOfArticles.addAll(items)
        notifyItemRangeInserted(this.listOfArticles.size - items.size, items.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            headerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.listitem_article_header, parent, false)
            ArticlesHeaderViewHolder(headerBinding, articleHandlers = articleHandlers)
        } else {
            articleBinding = DataBindingUtil.inflate(layoutInflater, R.layout.listitem_article, parent, false)
            ArticlesViewHolder(articleBinding, articleHandlers = articleHandlers)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int = listOfArticles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeader(position)) {
            val layoutParams = headerBinding.root.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = true
            context?.let { (holder as ArticlesHeaderViewHolder).bindData(listOfArticles[position]) }
            return
        }
        context?.let { (holder as ArticlesViewHolder).bindData(listOfArticles[position]) }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) ITEM_VIEW_TYPE_HEADER
        else ITEM_VIEW_TYPE_ITEM
    }

    class ArticlesViewHolder(
        private val binding: ListitemArticleBinding,
        private val articleHandlers: ArticleHandlers
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Articles) {
            binding.article = article
            binding.articleHandlers = articleHandlers
            binding.executePendingBindings()
        }
    }

    class ArticlesHeaderViewHolder(
        private val binding: ListitemArticleHeaderBinding,
        private val articleHandlers: ArticleHandlers
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(article: Articles) {
            binding.article = article
            binding.articleHandlers = articleHandlers
            binding.executePendingBindings()
        }
    }

    private fun isHeader(position: Int): Boolean {
        return position % 7 == 0
    }
}