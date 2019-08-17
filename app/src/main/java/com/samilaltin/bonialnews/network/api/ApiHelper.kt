package com.samilaltin.bonialnews.network.api


import com.samilaltin.bonialnews.BuildConfig
import com.samilaltin.bonialnews.data.model.response.HomeResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by samilaltin on 09/08/2019
 */
interface ApiHelper {

    companion object {

        fun createRetrofit(): ApiHelper {

            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor(LoggingInterceptor())
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiHelper::class.java)
        }
    }

    @GET("/v2/top-headlines")
    fun getHome(@Query("country") lang: String, @Query("page") page: Int, @Query("apiKey") apiKey: String): Observable<HomeResponse>
}