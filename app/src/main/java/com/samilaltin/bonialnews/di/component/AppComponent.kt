package com.samilaltin.bonialnews.di.component

import android.app.Application
import com.samilaltin.bonialnews.App
import com.samilaltin.bonialnews.di.builder.ActivityBuilder
import com.samilaltin.bonialnews.di.module.AppModule
import com.samilaltin.bonialnews.utility.module.UtilModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by samilaltin on 11/08/2019
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (UtilModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}