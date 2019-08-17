package com.samilaltin.bonialnews.ui.base.interactor

import com.samilaltin.bonialnews.data.preferences.PreferenceHelper
import com.samilaltin.bonialnews.network.api.ApiHelperImp

/**
 * Created by samilaltin on 09/08/2019
 */
open class BaseInteractorImp() {

    protected lateinit var preferenceHelper: PreferenceHelper
    protected lateinit var apiHelperImp: ApiHelperImp

    constructor(preferenceHelper: PreferenceHelper, apiHelperImp: ApiHelperImp) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelperImp = apiHelperImp
    }
}