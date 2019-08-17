package com.samilaltin.bonialnews.data.model.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by samilaltin on 09/08/2019
 */
@Parcelize
data class Source(
    @ColumnInfo(name = "sourceId") @SerializedName("id") var id: String?,
    @ColumnInfo(name = "name") @SerializedName("name") var name: String?
) : Parcelable {
    constructor() : this("", "")
}