package com.samilaltin.bonialnews.database

import androidx.room.*
import com.samilaltin.bonialnews.data.model.response.Articles
import androidx.room.OnConflictStrategy

/**
 * Created by samilaltin on 16/08/2019
 */
@Dao
interface ArticlesDao {

    @Query("SELECT * FROM db_articles")
    fun getAllArticles(): List<Articles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticles(articles: List<Articles>)

    @Query("DELETE FROM db_articles")
    fun deleteAll()
}
