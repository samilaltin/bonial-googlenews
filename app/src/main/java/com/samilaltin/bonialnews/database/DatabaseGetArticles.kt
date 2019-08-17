package com.samilaltin.bonialnews.database

import android.content.Context
import android.os.AsyncTask
import com.samilaltin.bonialnews.data.model.response.Articles
import com.samilaltin.bonialnews.ui.main.view.MainActivity

/**
 * Created by samilaltin on 16/08/2019
 */
object DatabaseGetArticles {

    fun getArticlesList(article: List<Articles>): List<Articles> {
        return article
    }

    fun getArticleDBAsync(db: AppDatabase, context: Context) {
        val task = GetArticleDbAsync(db, context)
        task.execute()
    }

    class GetArticleDbAsync internal constructor(private val mDb: AppDatabase, private val mContext: Context) :
        AsyncTask<Void, Void, List<Articles>>() {

        override fun doInBackground(vararg params: Void): List<Articles> {
            return getArticlesList(mDb.articlesDao().getAllArticles())
        }

        override fun onPostExecute(articles: List<Articles>) {
            (mContext as MainActivity).setOfflineAdapter(articles, articles.size)
        }
    }
}
