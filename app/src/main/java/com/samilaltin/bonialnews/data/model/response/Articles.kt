package com.samilaltin.bonialnews.data.model.response

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by samilaltin on 09/08/2019
 */
@Entity(tableName = "db_articles")
@Parcelize
data class Articles(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int?,
    @Embedded @SerializedName("source") var source: Source?,
    @ColumnInfo(name = "author") @SerializedName("author") var author: String?,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String?,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String?,
    @ColumnInfo(name = "url") @SerializedName("url") var url: String = "",
    @ColumnInfo(name = "urlToImage") @SerializedName("urlToImage") var urlToImage: String?,
    @ColumnInfo(name = "publishedAt") @SerializedName("publishedAt") var publishedAt: String?,
    @ColumnInfo(name = "content") @SerializedName("content") var content: String?
) : Parcelable {
    constructor() : this(null, null, "", "", "", "", "", "", "")
}