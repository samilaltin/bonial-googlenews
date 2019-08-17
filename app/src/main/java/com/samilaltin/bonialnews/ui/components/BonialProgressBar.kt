package com.samilaltin.bonialnews.ui.components

import android.content.Context
import android.view.View
import com.airbnb.lottie.LottieAnimationView

/**
 * Created by samilaltin on 13/08/2019
 */
class BonialProgressBar {
    private var progress: LottieAnimationView? = null

    companion object {
        private var mCShowProgress: BonialProgressBar? = null
        val instance: BonialProgressBar
            get() {
                if (mCShowProgress == null) {
                    mCShowProgress =
                        BonialProgressBar()
                }
                return mCShowProgress as BonialProgressBar
            }
    }

    fun showProgress(mContext: Context) {
        progress = LottieAnimationView(mContext)
        progress!!.visibility = View.VISIBLE
    }

    fun hideProgress() {
        if (progress != null) {
            progress!!.visibility = View.GONE
            progress = null
        }
    }
}
