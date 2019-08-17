package com.samilaltin.bonialnews.ui.main.interactor


import com.samilaltin.bonialnews.data.model.response.HomeResponse
import com.samilaltin.bonialnews.data.preferences.PreferenceHelper
import com.samilaltin.bonialnews.network.api.ApiError
import com.samilaltin.bonialnews.network.api.ApiHelperImp
import com.samilaltin.bonialnews.ui.base.interactor.BaseInteractorImp
import com.samilaltin.bonialnews.ui.base.view.BaseActivity
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by samilaltin on 09/08/2019
 */
class MainInteractorImp @Inject internal constructor(
    preferenceHelper: PreferenceHelper,
    apiHelperImp: ApiHelperImp
) :
    BaseInteractorImp(preferenceHelper = preferenceHelper, apiHelperImp = apiHelperImp),
    MainInteractor {

    override fun getHome(
        baseActivity: BaseActivity,
        lang: String,
        page: Int,
        success: (HomeResponse) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return apiHelperImp.getHome(
            preferenceHelper.getAppLanguage()!!, page, success
            , failure, terminate
        )
    }

}


