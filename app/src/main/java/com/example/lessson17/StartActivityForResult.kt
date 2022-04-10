package com.example.lessson17

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivityForResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_activity_for_result)

        val buttonEdit = findViewById<Button>(R.id.button_edit)
        buttonEdit.setOnClickListener {
            editNumber()
        }

        Log.d(TAG, "Activity create")
    }

    private fun editNumber() {

        val errorText = findViewById<TextView>(R.id.editCounterError)
        errorText.text = null
        val newCounterActivity = Intent(this, CounterActivity::class.java)
        val newCounter: EditText = findViewById(R.id.editNumber)

        if (valueNotBlanked(newCounter)) {
            newCounterActivity.putExtra("newValue", newCounter.text.toString().toInt())
            startActivity(newCounterActivity)
        } else {
            errorText.text = "Value is blanked"
        }
    }

    companion object {
        private const val TAG = "start activity for result"
    }

    private fun valueNotBlanked(x: EditText): Boolean {
        return (x.text.isNotEmpty())
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