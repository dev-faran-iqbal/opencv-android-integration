package com.faraniqbal.opencv.opencvintegrationapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.faraniqbal.opencv.opencvintegrationapp.databinding.ActivityMainBinding
import com.faraniqbal.opencv.opencvintegrationapp.utils.OpenCvUtils
import org.opencv.android.OpenCVLoader
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    lateinit var bindings: ActivityMainBinding

    val fileName :String = "input_image.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)


        if (OpenCVLoader.initLocal()) {
            Log.i("TAG", "OpenCV sdk loaded.")
            val bitmap = loadImage()
            val grayScaleBitmap = loadGrayScale(bitmap)
            displayGrayScale(grayScaleBitmap)
        } else {
            Log.i("TAG", "OpenCV sdk not loaded.")
        }
    }

    private fun loadImage(): Bitmap? {
        try {
            val ims: InputStream = this.assets.open(fileName)
            val drawable = Drawable.createFromStream(ims, null)
            drawable?.let {
                return OpenCvUtils.drawableToBitmap(it)
            }
        } catch (e: Exception) {
            Log.d("TAG", "loadImage Exp:\n ${e.localizedMessage}")
        }
        return null
    }

    private fun loadGrayScale(bitmap: Bitmap?) : Bitmap? {
        bitmap?.let {
            return OpenCvUtils.convertBitmapToGrayScale(bitmap)
        }
        return null
    }

    private fun displayGrayScale(grayScaleBitmap: Bitmap?) {
        grayScaleBitmap?.let { grayScale ->
            bindings.ivInputImage.setImageBitmap(grayScale)
        }
    }
}