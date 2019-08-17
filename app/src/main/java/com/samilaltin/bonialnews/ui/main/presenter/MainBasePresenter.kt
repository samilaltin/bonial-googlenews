package com.samilaltin.bonialnews.ui.main.presenter

import com.samilaltin.bonialnews.ui.base.presenter.BasePresenter
import com.samilaltin.bonialnews.ui.base.view.BaseActivity
import com.samilaltin.bonialnews.ui.main.interactor.MainInteractor
import com.samilaltin.bonialnews.ui.main.view.MainView


/**
 * Created by samilaltin on 09/08/2019
 */
interface MainBasePresenter<V : MainView, I : MainInteractor> : BasePresenter<V, I> {

    fun getArticles(baseActivity: BaseActivity, lang: String, page: Int)
}