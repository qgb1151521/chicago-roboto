package com.chicagoroboto.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.CheckResult
import android.support.annotation.DrawableRes
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.content.ContextCompat

object DrawableUtils {

  /**
   * Creates a [Drawable] by either creating it from a vector drawable resource or falling back
   * to a plain old drawable resource.
   */
  @CheckResult fun create(context: Context, @DrawableRes resourceId: Int): Drawable? {
    var drawable: Drawable?
    try {
      drawable = VectorDrawableCompat.create(context.resources, resourceId, context.theme)
    } catch (exception: Resources.NotFoundException) {
      drawable = ContextCompat.getDrawable(context, resourceId)
    }
    return drawable
  }
}
