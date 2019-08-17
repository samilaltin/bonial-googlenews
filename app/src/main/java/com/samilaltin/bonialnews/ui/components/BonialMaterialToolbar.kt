package com.samilaltin.bonialnews.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.samilaltin.bonialnews.R
import com.samilaltin.bonialnews.utility.AppBarUtils

/**
 * Created by samilaltin on 13/08/2019
 */
class BonialMaterialToolbar : Toolbar {
    @BindView(R.id.appBarTitleTV)
    lateinit var appBarTitleTV: TextView
    @BindView(R.id.appBarArea)
    lateinit var appBarArea: RelativeLayout
    private var isMarginsSet = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.bonial_material_toolbar, this)
        ButterKnife.bind(this)
        setContentInsetsRelative(0, 0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isMarginsSet) {
            isMarginsSet = true
            val layoutParams = layoutParams as RelativeLayout.LayoutParams
            layoutParams.topMargin = AppBarUtils.getStatusBarHeight(context)
            requestLayout()
        }
    }

    fun setTitle(title: String) {
        this.appBarTitleTV.text = title
    }

    fun setSetBgColor(color: Int) {
        setBackgroundColor(color)
    }
}
