package com.example.lessson17

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CounterActivity : AppCompatActivity() {

    private var counterValue = INITIAL_COUNTER_VALUE
    private var backGroundColor: String? = null
    private var txtColor: String? = null
    private lateinit var infoTextView: TextView
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        Log.d(TAG, "Activity create")
        val startForResultActivity = Intent(this, StartActivityForResult::class.java)
        rootView = findViewById(R.id.root)
        infoTextView = findViewById(R.id.tv_info)

        updateCounter(intent.getIntExtra("newValue", counterValue))
        updateCounter(intent.getIntExtra("counterValue", counterValue))
        intent.getStringExtra("textColor")?.let { setTxtColor(it) }
        intent.getStringExtra("bckgrndColor")?.let { setBg(it) }

        findViewById<View>(R.id.button_edit).setOnClickListener {
            startActivity(startForResultActivity)
        }

        findViewById<View>(R.id.btn_counter_minus).setOnClickListener {
            updateCounter(counterValue - STEP_OF_THE_COUNTER)
        }
        findViewById<View>(R.id.btn_counter_plus).setOnClickListener {
            updateCounter(counterValue + STEP_OF_THE_COUNTER)

        }
        findViewById<View>(R.id.btn_counter_rnd).setOnClickListener {
            updateCounter((MIN_RANDOM_VALUE..MAX_RANDOM_VALUE).random())

        }
        findViewById<View>(R.id.btn_counter_0).setOnClickListener {
            updateCounter(INITIAL_COUNTER_VALUE)
        }

        findViewById<Button>(R.id.button_share).setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            val shareBody = counterValue.toString()
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(share, shareBody))
        }
    }

    private fun updateCounter(value: Int) {
        counterValue = value
        infoTextView.text = value.toString()
    }

    fun colorText(view: View) {
        setTxtColor((view as Button).text.toString())
    }

    fun setTxtColor(colorText: String) {
        val setColor = when (colorText) {
            "r" -> Color.RED
            "g" -> Color.GREEN
            "b" -> Color.BLUE
            "m" -> Color.MAGENTA
            else -> Color.BLACK
        }
        txtColor = setColor.toString()
        infoTextView.setTextColor(setColor)
    }

    fun colorBg(view: View) {
        setBg((view as Button).text.toString())
    }

    fun setBg(colorBg: String) {
        val setColor = when (colorBg) {
            "1" -> getColor(R.color.white_204)
            "2" -> getColor(R.color.white_221)
            "3" -> getColor(R.color.white_238)
            else -> getColor(R.color.white)
        }
        backGroundColor = setColor.toString()
        rootView.setBackgroundColor(setColor)
    }

    companion object {
        private const val COUNTER_VALUE = "counterValue"
        private const val COLOR_TEXT = "colorText"
        private const val COLOR_BACKGR = "colorBG"
        private const val INITIAL_COUNTER_VALUE = 0
        private const val STEP_OF_THE_COUNTER = 1
        private const val MIN_RANDOM_VALUE = -100
        private const val MAX_RANDOM_VALUE = 100
        private const val TAG = "counter activity"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNTER_VALUE, counterValue)
        outState.putString(COLOR_TEXT, txtColor)
        outState.putString(COLOR_BACKGR, backGroundColor)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        updateCounter(savedInstanceState.getInt(COUNTER_VALUE))
        savedInstanceState.getString(COLOR_TEXT)?.let { setTxtColor(it) }
        savedInstanceState.getString(COLOR_BACKGR)?.let { setBg(it) }
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