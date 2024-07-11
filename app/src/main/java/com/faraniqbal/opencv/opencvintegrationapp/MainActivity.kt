package com.faraniqbal.opencv.opencvintegrationapp

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
            loadAndGrayScaleImage()
        } else {
            Log.i("TAG", "OpenCV sdk not loaded.")
        }
    }

    private fun loadAndGrayScaleImage() {
        try {
            val ims: InputStream = this.assets.open(fileName)
            val drawable = Drawable.createFromStream(ims, null)
            drawable?.let {
                val bitmap = OpenCvUtils.drawableToBitmap(it)
                val output = OpenCvUtils.convertBitmapToGrayScale(bitmap)
                output.let { out ->
                    bindings.ivInputImage.setImageBitmap(out)
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "loadAndGrayScaleImage Exp:\n ${e.localizedMessage}")
        }
    }
}