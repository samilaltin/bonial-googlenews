package com.samilaltin.bonialnews.utility.provider

import android.content.Context
import com.samilaltin.bonialnews.data.preferences.AppPreferenceHelper
import javax.inject.Inject

/**
 * Created by samilaltin on 12/08/2019
 */
class AppLanguageProvider @Inject constructor() {
    private var appLanguage: String? = null


    fun getAppLanguage(): String {
        return if (appLanguage != null) appLanguage!! else ""
    }

    fun getAppLanguage(context: Context): String {
        if (appLanguage == null) {
            appLanguage = AppPreferenceHelper(context, AppPreferenceHelper.PREF_KEY_CURRENT_LANGUAGE).getAppLanguage()
        }
        return when (appLanguage) {
            ENGLISH_CODE -> ENGLISH
            TURKISH_CODE -> TURKISH
            else -> DEFAULT_LANG
        }
    }

    fun checkLanguageSupport(language: String): Boolean {
        return availableLanguageCodes.contains(language)
    }

    companion object {
        const val ENGLISH = "English"
        const val TURKISH = "Turkish"
        const val ENGLISH_CODE = "en"
        const val TURKISH_CODE = "tr"
        const val DEFAULT_LANG = TURKISH_CODE

        private var appLanguageProvider: AppLanguageProvider? = null

        val instance: AppLanguageProvider
            get() {
                if (appLanguageProvider == null) {
                    appLanguageProvider = AppLanguageProvider()
                }
                return appLanguageProvider as AppLanguageProvider
            }

        private val availableLanguages: List<String>
            get() {
                val languages: ArrayList<String> = ArrayList()
                languages.add(ENGLISH)
                languages.add(TURKISH)
                return languages
            }

        private val availableLanguageCodes: List<String>
            get() {
                val languages: ArrayList<String> = ArrayList()
                languages.add(ENGLISH_CODE)
                languages.add(TURKISH_CODE)
                return languages
            }
    }
}