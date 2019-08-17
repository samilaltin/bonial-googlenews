package com.samilaltin.bonialnews.di.builder

import com.samilaltin.bonialnews.ui.main.MainActivityModule
import com.samilaltin.bonialnews.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by samilaltin on 11/08/2019
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

}