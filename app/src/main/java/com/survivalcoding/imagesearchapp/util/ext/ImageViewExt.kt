package com.survivalcoding.imagesearchapp.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.setUrl(url: String) {
    Glide.with(this)
        .load(url)
        .transform(RoundedCorners(20))
        .into(this)
}