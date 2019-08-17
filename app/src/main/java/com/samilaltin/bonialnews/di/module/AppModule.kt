package com.samilaltin.bonialnews.di.module

import android.app.Application
import android.content.Context
import com.samilaltin.bonialnews.BuildConfig
import com.samilaltin.bonialnews.data.preferences.AppPreferenceHelper
import com.samilaltin.bonialnews.data.preferences.PreferenceHelper
import com.samilaltin.bonialnews.di.ApiKeyInfo
import com.samilaltin.bonialnews.di.PreferenceInfo
import com.samilaltin.bonialnews.network.api.ApiHelper
import com.samilaltin.bonialnews.network.api.ApiHelperImp
import com.samilaltin.bonialnews.utility.AppConstants
import com.samilaltin.bonialnews.utility.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by samilaltin on 09/08/2019
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application


    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.X_APP_KEY

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper


    @Provides
    @Singleton
    fun providesRemoteService(): ApiHelper {
        return ApiHelper.createRetrofit()
    }

    @Provides
    @Singleton
    fun providesAppApi(): ApiHelperImp {
        return ApiHelperImp(
            apiHelper = providesRemoteService()
        )
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

}