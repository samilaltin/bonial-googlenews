package com.samilaltin.bonialnews.data.preferences


/**
 * Created by samilaltin on 09/08/2019
 */
interface PreferenceHelper {

    fun setAppLanguage(language: String?)

    fun getAppLanguage(): String?

}