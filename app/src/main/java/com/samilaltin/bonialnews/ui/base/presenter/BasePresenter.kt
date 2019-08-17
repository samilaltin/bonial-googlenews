package com.samilaltin.bonialnews.ui.base.presenter

import com.samilaltin.bonialnews.ui.base.view.BaseView


/**
 * Created by samilaltin on 09/08/2019
 */
interface BasePresenter<V : BaseView, I> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}