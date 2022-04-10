package com.example.lessson17

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreateCounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_counter)

        Log.d(TAG, "Activity create")

        val buttonCreate = findViewById<Button>(R.id.button_create_counter)
        buttonCreate.setOnClickListener {
            createCounter()
        }
    }

    fun createCounter() {
        val errorText = findViewById<TextView>(R.id.textViewError)
        errorText.text = null
        val counterValue: EditText = findViewById(R.id.counter_value)
        val textColor: EditText = findViewById(R.id.editColor)
        val backgroundColor: EditText = findViewById(R.id.editBackground)

        if (colorIsRGBM(textColor) &&
            bckgrndInRange(backgroundColor) &&
            valueNotBlanked(counterValue)
        ) {
            val counterActivity = Intent(this, CounterActivity::class.java)
            counterActivity.putExtra("counterValue", counterValue.text.toString().toInt())
            counterActivity.putExtra("textColor", textColor.text.toString().lowercase())
            counterActivity.putExtra("bckgrndColor", backgroundColor.text.toString())

            startActivity(counterActivity)
        } else {

            if (!valueNotBlanked(counterValue)) {
                counterValue.text.clear()
                errorText.text = "${errorText.text}Invalid value \n"
            }
            if (!colorIsRGBM(textColor)) {
                textColor.text.clear()
                errorText.text = "${errorText.text}Invalid color value \n"
            }
            if (!bckgrndInRange(backgroundColor)) {
                backgroundColor.text.clear()
                errorText.text = "${errorText.text}Invalid background value \n"
            }
        }
    }

    companion object {
        val RGBM = arrayOf("r", "g", "b", "m")
        val BACKGROUND_RANGE = arrayOf(1, 2, 3, 4)
        private const val TAG = "create counter activity"
    }

    fun colorIsRGBM(x: EditText): Boolean {
        return (RGBM.contains(x.text.toString().lowercase()))
    }

    fun valueNotBlanked(x: EditText): Boolean {
        return (x.text.isNotEmpty())
    }

    fun bckgrndInRange(x: EditText): Boolean {
        return (BACKGROUND_RANGE.contains(x.text.toString().toIntOrNull()))
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