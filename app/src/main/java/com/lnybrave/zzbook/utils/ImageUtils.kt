package com.lnybrave.zzbook.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by lny on 2017/7/29.
 */
fun loadBookCover(url: String?, view: ImageView) {
    if (url != null) {
        Picasso.with(view.context).load(url).into(view)
    }
}