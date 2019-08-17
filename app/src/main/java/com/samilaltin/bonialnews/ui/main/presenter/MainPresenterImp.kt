package com.samilaltin.bonialnews.ui.main.presenter

import com.samilaltin.bonialnews.ui.base.presenter.BasePresenterImp
import com.samilaltin.bonialnews.ui.base.view.BaseActivity
import com.samilaltin.bonialnews.ui.main.interactor.MainInteractor
import com.samilaltin.bonialnews.ui.main.view.MainView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by samilaltin on 09/08/2019
 */
class MainPresenterImp<V : MainView, I : MainInteractor> @Inject internal constructor(
    interactor: I,
    disposable: CompositeDisposable
) : BasePresenterImp<V, I>(
    interactor = interactor,
    compositeDisposable = disposable
), MainBasePresenter<V, I> {

    override fun getArticles(baseActivity: BaseActivity, lang: String, page: Int) {
        interactor?.let { it ->
            it.getHome(baseActivity, lang, page, {
                getView()?.openHome(it)
            }, {
                getView()?.fail()
            }, {
                getView()?.terminate()
            }).also {
                compositeDisposable.add(it)
            }
        }
    }
}