package com.samilaltin.bonialnews.database

import android.content.Context
import androidx.room.Room

/**
 * Created by samilaltin on 16/08/2019
 */
class DatabaseClient private constructor(context: Context) {

    val appDatabase: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "db_articles").build()

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(context)
            }
            return mInstance as DatabaseClient
        }
    }
}
