package com.samilaltin.bonialnews.database

import android.os.AsyncTask
import com.samilaltin.bonialnews.data.model.response.Articles

/**
 * Created by samilaltin on 16/08/2019
 */
object DatabaseInitializer {

    fun populateArticleDBAsync(db: AppDatabase, articles: List<Articles>, deleteDb: Boolean) {
        val task = PopulateDbAsync(db, articles, deleteDb)
        task.execute()
    }

    private fun addArticle(db: AppDatabase, user: List<Articles>): List<Articles> {
        db.articlesDao().insertAllArticles(user)
        return user
    }

    private fun deleteArticles(db: AppDatabase) {
        db.articlesDao().deleteAll()
    }

    private fun populateWithArticles(db: AppDatabase, articles: List<Articles>) {
        addArticle(db, articles)
    }

    class PopulateDbAsync internal constructor(
        private val mDb: AppDatabase,
        private val article: List<Articles>,
        private val deleteDB: Boolean
    ) : AsyncTask<Void, Void, List<Articles>>() {

        override fun doInBackground(vararg params: Void): List<Articles>? {
            if (deleteDB) {
                deleteArticles(mDb)
            }
            populateWithArticles(mDb, article)
            return null
        }
    }
}
