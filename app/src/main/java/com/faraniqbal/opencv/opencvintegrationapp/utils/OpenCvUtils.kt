package com.faraniqbal.opencv.opencvintegrationapp.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class OpenCvUtils {
    companion object {

        fun drawableToBitmap(drawable: Drawable): Bitmap {
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 1
            val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 1
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }


        fun convertBitmapToGrayScale(image: Bitmap): Bitmap {
            val tmp = Mat(image.width, image.height, CvType.CV_8UC1)
            Utils.bitmapToMat(image, tmp)
            Imgproc.cvtColor(tmp, tmp, Imgproc.COLOR_RGB2GRAY)

            val mBitmap = Bitmap.createBitmap(tmp.width(), tmp.height(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(tmp, mBitmap)
            return mBitmap
        }
    }
}