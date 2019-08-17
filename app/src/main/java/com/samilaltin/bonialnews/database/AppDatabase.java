package com.samilaltin.bonialnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.samilaltin.bonialnews.data.model.response.Articles;


/**
 * Created by samilaltin on 16/08/2019
 */
@Database(entities = {Articles.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    abstract ArticlesDao articlesDao();
}
