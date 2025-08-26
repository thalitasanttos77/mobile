package com.example.tipcalcapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var currentBillTotal: Double = 0.00
    var currentCustomPercent: Int = 18
    lateinit var tip10EditText: EditText
    lateinit var tip15EditText: EditText
    lateinit var tip20EditText: EditText
    lateinit var total10EditText: EditText
    lateinit var total15EditText: EditText
    lateinit var total20EditText: EditText
    lateinit var billEditText: EditText
    lateinit var tipCustomEditText: EditText
    lateinit var totalCustomEditText: EditText
    lateinit var customTipTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tableLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tip10EditText = findViewById(R.id.tip10EditText)
        tip15EditText = findViewById(R.id.tip15EditText)
        tip20EditText = findViewById(R.id.tip20EditText)
        total10EditText = findViewById(R.id.total10EditText)
        total15EditText = findViewById(R.id.total15EditText)
        total20EditText = findViewById(R.id.total20EditText)
        billEditText = findViewById(R.id.billEditText)
        tipCustomEditText = findViewById(R.id.tipCustomEditText)
        totalCustomEditText = findViewById(R.id.totalCustomEditText)
        customTipTextView = findViewById(R.id.customTipTextView)

        var customSeekBar =findViewById<SeekBar>(R.id.customSeekBar)

        currentCustomPercent = customSeekBar.progress
        billEditText.addTextChangedListener(billTextWatcher)
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener)

    }

    @SuppressLint("DefaultLocale")
    private fun updateStandard() {
        val tenPercentTip = currentBillTotal * 0.10
        val tenPercentTotal = currentBillTotal + tenPercentTip
        val fifteenPercentTip = currentBillTotal * 0.15
        val fifteenPercentTotal = currentBillTotal + fifteenPercentTip
        val twentyPercentTip = currentBillTotal * 0.20
        val twentyPercentTotal = currentBillTotal + twentyPercentTip

        tip10EditText.setText(String.format("%.2f", tenPercentTip))
        total10EditText.setText(String.format("%.2f", tenPercentTotal))
        tip15EditText.setText(String.format("%.2f", fifteenPercentTip))
        total15EditText.setText(String.format("%.2f", fifteenPercentTotal))
        tip20EditText.setText(String.format("%.2f", twentyPercentTip))
        total20EditText.setText(String.format("%.2f", twentyPercentTotal))
    }

    @SuppressLint("DefaultLocale")
    private fun updateCustom() {
        customTipTextView.text = "$currentCustomPercent%"

        val customTipAmount = currentBillTotal * currentCustomPercent * 0.01
        val customTotalAmount = currentBillTotal + customTipAmount

        tipCustomEditText.setText(String.format("%.2f", customTipAmount))
        totalCustomEditText.setText(String.format("%.2f", customTotalAmount))
    }
    private val customSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            currentCustomPercent = progress
            updateCustom()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }
    private val billTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                currentBillTotal = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentBillTotal = 0.0
            }
            updateStandard()
            updateCustom()
        }
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    }
}