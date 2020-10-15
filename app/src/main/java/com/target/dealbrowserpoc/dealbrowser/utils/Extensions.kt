package com.target.dealbrowserpoc.dealbrowser.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


fun ImageView.loadImageUrl(
    imageUrl: String?,
    requestOptions: RequestOptions = RequestOptions.centerCropTransform(),
    roundedCorners: Boolean = false, width: Int = 0, height: Int = 0
    , placeholder: Int? = null,
    listenImage: (Boolean) -> Unit = {}
) {

    val options = if (roundedCorners) {
        RequestOptions().transforms(CenterCrop(), RoundedCorners(8))
    } else requestOptions

    val builder = Glide.with(this)
        .load(imageUrl)
        .apply(options.priority(Priority.HIGH))

    if (width != 0)
        builder.override(width, height)

    if (placeholder != null)
        builder.placeholder(placeholder)

    builder.addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            listenImage(false)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            listenImage(true)
            return false
        }

    })
    builder.into(this)
}
