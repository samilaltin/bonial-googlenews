package com.samilaltin.bonialnews.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by samilaltin on 10/08/2019
 */
object CommonUtil {

    var publishedTime = ""

    @Suppress("DEPRECATION")
    fun isOnline(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            connectivityManager?.run {
                connectivityManager.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    @JvmStatic
    fun publishedAt(date: String): String {
        val second = 1000
        val minute = 60 * second
        val hour = 60 * minute
        val day = 24 * hour
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val result = dateFormat.parse(date)
        val c = Calendar.getInstance().time
        if (result != null) {
            val remaining = c.time - result.time
            publishedTime = when {
                remaining < hour -> (remaining / minute).toString()
                remaining < day -> (remaining / hour).toString()
                else -> (remaining / hour).toString()
            }
        }
        return publishedTime
    }
}