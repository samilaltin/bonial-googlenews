package com.samilaltin.bonialnews.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.samilaltin.bonialnews.di.PreferenceInfo
import javax.inject.Inject

/**
 * Created by samilaltin on 09/08/2019
 */
class AppPreferenceHelper @Inject constructor(
    context: Context,
    @PreferenceInfo private val prefFileName: String
) : PreferenceHelper {

    companion object {
        val PREF_KEY_CURRENT_LANGUAGE = "PREF_KEY_CURRENT_LANGUAGE"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setAppLanguage(language: String?) = mPrefs.edit { putString(PREF_KEY_CURRENT_LANGUAGE, language) }

    override fun getAppLanguage(): String? = mPrefs.getString(PREF_KEY_CURRENT_LANGUAGE, "us")
}