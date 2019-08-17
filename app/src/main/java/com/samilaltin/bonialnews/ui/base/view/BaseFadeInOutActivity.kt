package com.samilaltin.bonialnews.ui.base.view

import android.content.Intent
import com.samilaltin.bonialnews.utility.transition.Transition

/**
 * Created by samilaltin on 09/08/2019
 */
abstract class BaseFadeInOutActivity : BaseActivity() {

    private var transition: Transition = Transition.TransitionFadeInOut()

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(transition.enterAnim, transition.exitAnim)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(transition.enterAnimBack, transition.exitAnimBack)
    }

    fun setTransition(transition: Transition) {
        this.transition = transition
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }
}