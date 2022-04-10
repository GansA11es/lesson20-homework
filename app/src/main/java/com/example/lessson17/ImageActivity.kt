package com.example.lessson17

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity

class ImageActivity : AppCompatActivity() {

    @DrawableRes
    private var currentImageRes = R.drawable.cat
    private var rotation = IMAGE_ANGLE_START
    private var savedAngle: Float? = 0f
    private lateinit var imageZooMap: Map<String, Int>
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        Log.d(TAG, "Activity create")

        imageView = findViewById(R.id.imageView)

        imageZooMap =
            mapOf(
                getString(R.string.button_cat) to R.drawable.cat,
                getString(R.string.button_dog) to R.drawable.dog,
                getString(R.string.button_parrot) to R.drawable.parrot
            )

        imageView.setOnClickListener {
            updateRotation(rotation + ROTATION_BY_CLICK)
        }
    }

    fun setImage(view: View) {
        val text = (view as Button).text
        var imageRes = imageZooMap[text]
        if (imageRes == null) {
            imageRes = (imageZooMap.values - currentImageRes).random()
        }
        updateRotation(IMAGE_ANGLE_START)
        currentImageRes = imageRes
        imageView.setImageResource(imageRes)
    }

    private fun updateRotation(angle: Float) {
        rotation = angle
        savedAngle = angle
        imageView.rotation = rotation
    }

    companion object {
        private const val SAVED_IMAGE = "savedImage"
        private const val SAVED_ROTATION_ANGLE = "savedRotationAngle"
        private const val TAG = "image activity"
        private const val ROTATION_BY_CLICK = 90f
        private const val IMAGE_ANGLE_START = 0f
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVED_IMAGE, currentImageRes)
        savedAngle?.let { outState.putFloat(SAVED_ROTATION_ANGLE, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentImageRes = savedInstanceState.getInt(SAVED_IMAGE)
        imageView.setImageResource(currentImageRes)
        updateRotation(savedInstanceState.getFloat(SAVED_ROTATION_ANGLE))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Activity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Activity paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Activity destroyed")
    }
}