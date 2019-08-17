package com.samilaltin.bonialnews.ui.main.handler

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


/**
 * Created by samilaltin on 16/08/2019
 */
class ImageBindingAdapters {
    companion object {
        @BindingAdapter(value = ["url", "placeHolder"], requireAll = false)
        @JvmStatic
        fun getImage(view: ImageView, url: String?, placeHolder: Drawable) {
            Picasso.get().load(url).placeholder(placeHolder).into(view)
        }
    }

}