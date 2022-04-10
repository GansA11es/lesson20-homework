package com.example.lessson17

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class InfoActivity : AppCompatActivity() {

    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        Log.d(TAG, "Activity create")

        infoTextView = findViewById(R.id.textView)
    }

    fun info(view: View) {
        when ((view as Button).text) {
            "device" -> {
                infoTextView.text = android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL

            }
            "time" -> {
                infoTextView.text = SimpleDateFormat("HH:mm", Locale.US).format(Date())
            }
            "toast" -> {
                Toast.makeText(this, "hello", Toast.LENGTH_LONG).show()
            }
            else -> error("unknown command")
        }
    }

    companion object {
        private const val TAG = "info activity"
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