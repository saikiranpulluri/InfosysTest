
package com.example.test.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// To hide the spinner once data is available.
@BindingAdapter("isNetworkError", "aboutList")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, aboutList: Any?) {
    view.visibility = if (aboutList != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}

// To display images from URL using Glide
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}