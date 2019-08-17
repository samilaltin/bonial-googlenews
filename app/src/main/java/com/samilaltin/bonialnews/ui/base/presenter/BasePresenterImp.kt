package com.samilaltin.bonialnews.ui.base.presenter


import com.samilaltin.bonialnews.ui.base.view.BaseView
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by samilaltin on 09/08/2019
 */
abstract class BasePresenterImp<V : BaseView, I> internal constructor(
    protected var interactor: I?,
    protected val compositeDisposable: CompositeDisposable
) : BasePresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}