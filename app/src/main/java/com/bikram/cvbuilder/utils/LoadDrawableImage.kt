package com.bikram.cvbuilder.utils

import android.R
import android.content.Context
import android.graphics.drawable.Drawable


class LoadDrawableImage {
    companion object {
        fun getDrawableImage(context: Context, uri: String): Int {
            return context.resources.getIdentifier(uri, null, context.packageName)
        }
    }
}