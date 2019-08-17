package com.samilaltin.bonialnews.ui.base.view

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import com.google.android.material.snackbar.Snackbar
import com.samilaltin.bonialnews.utility.AppBarUtils


/**
 * Created by samilaltin on 09/08/2019
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    open var rootView: ViewGroup? = null

    abstract fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup

    abstract fun bindView()

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setToolbar()
        rootView = onCreateActivity(savedInstanceState)
        bindView()
    }


    private fun setToolbar() {
        AppBarUtils.initStatusBar(this.window, this)
    }

    private fun performDI() = AndroidInjection.inject(this)


    override fun showSnackbar(text: String) {
        Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()
    }

}