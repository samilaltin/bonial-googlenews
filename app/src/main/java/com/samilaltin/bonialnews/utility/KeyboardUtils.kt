package com.samilaltin.bonialnews.utility

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.orhanobut.logger.Logger;

/**
 * Created by samilaltin on 10/08/2019
 */
object KeyboardUtils {

    fun hideKeyboard(context: Context) {
        try {
            val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow((context as Activity).currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            if (e.message != null)
                Logger.d("KeyboardError", e.message)
        }
    }
}