package com.clean.app.views.feeds

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.clean.app.R


/**
 * Created by rohit.anvekar on 14/7/20.
 */
object FeedsCustomBindings {

    /**
     * loadImageUrl() func load the given image url to view
     */
    @JvmStatic
    @BindingAdapter("android:imageUrl", "android:context")
    fun loadImageUrl(view: ImageView, imageUrl: String, context: Context) {
        Glide.with(context)
            .load(imageUrl)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transform(CenterCrop())
            .placeholder(getPlaceHolder(context))
            .error(R.drawable.ic_no_image)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    view.background = resource
                }

                override fun onLoadStarted(placeholder: Drawable?) {
                    view.background = placeholder
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    view.background = errorDrawable
                }
            })

    }

    /**
     * getPlaceHolder() func return the progressive view for placeholder
     */
    private fun getPlaceHolder(context: Context): CircularProgressDrawable {
        var circularPD = CircularProgressDrawable(context)
        circularPD.strokeWidth = 5f
        circularPD.centerRadius = 30f
        circularPD.start()
        return circularPD
    }

}