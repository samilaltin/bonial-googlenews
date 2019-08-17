package com.samilaltin.bonialnews.ui.main


import com.samilaltin.bonialnews.ui.main.interactor.MainInteractor
import com.samilaltin.bonialnews.ui.main.interactor.MainInteractorImp
import com.samilaltin.bonialnews.ui.main.presenter.MainBasePresenter
import com.samilaltin.bonialnews.ui.main.presenter.MainPresenterImp
import com.samilaltin.bonialnews.ui.main.view.MainView
import dagger.Module
import dagger.Provides


/**
 * Created by samilaltin on 10/08/2019
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractorImp: MainInteractorImp): MainInteractor = mainInteractorImp

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenterImp<MainView, MainInteractor>)
            : MainBasePresenter<MainView, MainInteractor> = mainPresenter
}