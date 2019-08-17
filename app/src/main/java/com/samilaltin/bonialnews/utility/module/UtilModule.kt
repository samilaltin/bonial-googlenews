package com.samilaltin.bonialnews.utility.module

import com.samilaltin.bonialnews.utility.provider.AppLanguageProvider
import dagger.Module
import dagger.Provides

/**
 * Created by samilaltin on 11/08/2019
 */
@Module
class UtilModule {

    @Provides
    internal fun provideAppLanguageProvider(): AppLanguageProvider =
        AppLanguageProvider.instance
}