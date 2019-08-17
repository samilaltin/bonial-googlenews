package com.samilaltin.bonialnews.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by samilaltin on 09/08/2019
 */
@Parcelize
data class HomeResponse(
    @SerializedName("status") val title: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Articles>
) : Parcelable