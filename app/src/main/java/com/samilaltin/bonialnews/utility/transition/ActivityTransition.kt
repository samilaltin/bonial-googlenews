package com.samilaltin.bonialnews.utility.transition

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityOptionsCompat
import com.samilaltin.bonialnews.utility.KeyboardUtils
import java.lang.Exception
import java.lang.IllegalStateException

/**
 * Created by samilaltin on 11/08/2019
 */

class ActivityTransition(builder: Builder) {
    private val mActivity: Activity?
    private val mActivityOptionsCompat: ActivityOptionsCompat?
    private val mBundle: Bundle?
    private val mFlags: List<Int>?
    private val mTransition: Transition?
    private val mClass: Class<*>?
    private val clearTask: Boolean
    private val data: Uri?

    private val intent: Intent
        get() {
            val intent = Intent(mActivity, mClass)
            if (mFlags != null && mFlags.isNotEmpty()) {
                for (flag in mFlags) {
                    intent.addFlags(flag)
                }
            }
            if (mBundle != null) {
                intent.putExtras(mBundle)
            } else {
                intent.putExtras(Bundle())
            }
            if (data != null) {
                intent.data = data
            }
            return intent
        }

    init {
        this.mActivity = builder.mActivity
        this.mClass = builder.mClass
        this.mActivityOptionsCompat = builder.mActivityOptionsCompat
        this.mBundle = builder.mBundle
        this.mFlags = builder.mFlags
        this.mTransition = builder.mTransition
        this.clearTask = builder.clearTask
        this.data = builder.data
    }

    fun start() {
        mActivity?.let { KeyboardUtils.hideKeyboard(it) }
        startAct(intent)
    }

    private fun startAct(intent: Intent?) {
        if (mClass == null) {
            throw IllegalStateException("Target class can not be null!")
        }
        if (mActivity != null && intent != null) {
            val sdk = Build.VERSION.SDK_INT
            if (sdk >= Build.VERSION_CODES.JELLY_BEAN && mActivityOptionsCompat != null) {
                mActivity.startActivity(intent, mActivityOptionsCompat.toBundle())
            } else {
                mActivity.startActivity(intent)
            }
            if (mTransition != null) {
                transition(mActivity, mTransition.enterAnim, mTransition.exitAnim)
            }
            clearTaskIfNeeded()
        }
    }

    private fun clearTaskIfNeeded() {
        if (clearTask) {
            Handler().postDelayed({
                try {
                    mActivity?.finishAffinity()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 270)
        }
    }

    private fun transition(activity: Activity, enterAnim: Int, exitAnim: Int) {
        if (exitAnim != -1 && enterAnim != -1) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }

    class Builder(mActivity: Activity, mClass: Class<*>?) {
        internal var mActivity: Activity? = null
        internal var mActivityOptionsCompat: ActivityOptionsCompat? = null
        internal var mBundle: Bundle? = null
        internal var mFlags: MutableList<Int>? = null
        internal var mTransition: Transition = Transition.TransitionFadeInOut()
        internal var mClass: Class<*>? = null
        internal var clearTask = false
        internal var data: Uri? = null

        init {
            this.mActivity = mActivity
            this.mClass = mClass
        }

        fun build(): ActivityTransition {
            val activityTransition = ActivityTransition(this)
            if (activityTransition.mActivity == null) {
                throw IllegalStateException("Activity can not be null!")
            }
            return activityTransition
        }

        fun setBundle(mBundle: Bundle): Builder {
            this.mBundle = mBundle
            return this
        }


    }
}



