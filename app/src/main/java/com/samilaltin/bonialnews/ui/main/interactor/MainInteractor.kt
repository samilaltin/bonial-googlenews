package com.samilaltin.bonialnews.ui.main.interactor


import com.samilaltin.bonialnews.data.model.response.HomeResponse
import com.samilaltin.bonialnews.network.api.ApiError
import com.samilaltin.bonialnews.ui.base.view.BaseActivity
import io.reactivex.disposables.Disposable

/**
 * Created by samilaltin on 09/08/2019
 */
interface MainInteractor {

    fun getHome(
        baseActivity: BaseActivity, lang: String, page: Int, success: (HomeResponse) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

}