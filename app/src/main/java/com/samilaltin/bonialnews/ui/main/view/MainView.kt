package com.samilaltin.bonialnews.ui.main.view

import com.samilaltin.bonialnews.data.model.response.HomeResponse
import com.samilaltin.bonialnews.ui.base.view.BaseView

/**
 * Created by samilaltin on 09/08/2019
 */
interface MainView : BaseView {

    fun openHome(homeResponse: HomeResponse)
    fun fail()
    fun terminate()

}