# OpenCV Integration in Android Project

Integrating OpenCV into your Android project can greatly enhance your app’s ability to process images and videos. Whether you are working on a sophisticated computer vision application or just experimenting with image processing, OpenCV is a powerful tool that can help you achieve your goals. In this tutorial, I will guide you through the process of integrating OpenCV into an Android Studio project.

## Prerequisites

Before we begin, make sure you have the following installed on your development environment:

- Android Studio
- Basic knowledge of Android development
- OpenCV library

## Step 1: Setting Up the Project

1. **Create a New Project**: Open Android Studio and create a new project. Choose “Empty Activity” as your template.
2. **Configure the Project**: Fill in the required details like the name of your application, package name, and save location. Choose your desired language (Java or Kotlin). Click “Finish” to create your project.

## Step 2: Adding OpenCV to Your Project

1. **Download OpenCV for Android**: Go to the OpenCV website and download the latest OpenCV SDK for Android.
2. **Extract the SDK**: Extract the downloaded SDK to a convenient location on your computer.
3. **Copy OpenCV to Your Project**: 
    - Navigate to the extracted OpenCV SDK folder.
    - Inside the SDK folder, you will find the `sdk` directory. Copy the `sdk` directory.
    - Paste the `sdk` directory into the `app` directory of your Android Studio project.

4. **Edit Your build.gradle Files**:
    - Open `build.gradle` (Project level) and add the following repository:
    ```gradle
    allprojects {
        repositories {
            google()
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
    }
    ```
    - Open `build.gradle` (Module level) and add the following dependency:
    ```gradle
    dependencies {
        implementation project(':opencvsdk')
    }
    ```
5. **Sync Your Project**: Click “Sync Now” to sync your project with the added OpenCV dependency.

## Step 3: Configuring OpenCV

1. **Initialize OpenCV in Your MainActivity**:
    - Open `MainActivity.java` or `MainActivity.kt`.
    - Initialize OpenCV using the following code:
    ```java
    import org.opencv.android.OpenCVLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (OpenCVLoader.initLocal()) {
            Log.i("TAG", "OpenCV sdk loaded.");
        } else {
            Log.i("TAG", "OpenCV sdk not loaded.");
        }
    }
    ```
2. **Verify OpenCV**:
    - Run your application to ensure OpenCV initializes successfully. Check the Logcat for messages indicating whether the initialization was successful or not.

## Step 4: Using OpenCV for Image Processing

1. **Load an Image**: To load an image for processing, you can use OpenCV’s `Imgproc.cvtColor` class. Add the following code to your `MainActivity` and call a function from `OpenCvUtils`.
    ```kotlin
    import com.faraniqbal.opencv.opencvintegrationapp.utils.OpenCvUtils
    import android.graphics.Bitmap

    private fun loadImage(): Bitmap? {
        try {
            val ims: InputStream = this.assets.open(fileName)
            val drawable = Drawable.createFromStream(ims, null)
            drawable?.let {
                return OpenCvUtils.drawableToBitmap(it)
            }
        } catch (e: Exception) {
            Log.d("TAG", "loadImage Exp:\\n ${e.localizedMessage}")
        }
        return null
    }
    ```

2. **Process the Image**: Now that you have loaded an image, you can perform various image processing operations. Here’s an example of converting the image to grayscale:
    ```kotlin
    private fun loadGrayScale(bitmap: Bitmap?) : Bitmap? {
        bitmap?.let {
            return OpenCvUtils.convertBitmapToGrayScale(bitmap)
        }
        return null
    }
    ```

3. **Display the Processed Image**: To display the processed image in your app, you can convert the `Mat` object to a `Bitmap` and set it in an `ImageView`:
    ```kotlin
    import android.graphics.Bitmap;
    import android.widget.ImageView;

    private fun displayGrayScale(grayScaleBitmap: Bitmap?) {
        grayScaleBitmap?.let { grayScale ->
            bindings.ivInputImage.setImageBitmap(grayScale)
        }
    }
    ```

## Conclusion

Integrating OpenCV into your Android project opens up a world of possibilities for image and video processing. In this tutorial, we walked through the steps of setting up an Android project with OpenCV, initializing OpenCV, and performing basic image processing tasks. With OpenCV, you can implement complex computer vision algorithms and enhance your app’s capabilities.

## Source Code

Explore the project’s source code on GitHub! Dive into the details, understand how it works, and get hands-on with the code. The repository is your go-to place for transparency and collaboration.
"""
