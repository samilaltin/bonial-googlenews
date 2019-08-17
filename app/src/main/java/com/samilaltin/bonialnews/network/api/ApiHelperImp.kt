package com.samilaltin.bonialnews.network.api

import com.samilaltin.bonialnews.BuildConfig
import com.samilaltin.bonialnews.data.model.response.HomeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by samilaltin on 09/08/2019
 */
class ApiHelperImp @Inject constructor(
    private val apiHelper: ApiHelper
) {

    fun getHome(
        lang: String,
        page: Int,
        success: (HomeResponse) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable {

        return apiHelper.getHome(lang, page, BuildConfig.X_APP_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<HomeResponse>(
                    {
                        success(it)
                    },
                    failure
                )
            )
    }
}