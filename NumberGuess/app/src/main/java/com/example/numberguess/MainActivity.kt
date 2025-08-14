package com.example.numberguess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var sortedNumber: Int = 0
    private var tries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        sortedNumber = (1..100).random()
        tries = 0
        println("NÚMERO SORTEADO: $sortedNumber")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun guessNumber(view: View) {
        val input = findViewById<EditText>(R.id.editTextNumber)
        val output = findViewById<TextView>(R.id.textViewOutput)
        var tip: String

        if (input.text.isEmpty()) {
            Toast.makeText(this, "Digite um número!", Toast.LENGTH_SHORT).show()
            return
        }

        val inputNumber: Int = input.text.toString().toInt()
        tries++

        if (inputNumber > sortedNumber) {
            tip = "O número sorteado é MENOR!"
        } else if (inputNumber < sortedNumber) {
            tip = "O número sorteado é MAIOR!"
        } else {
            tip = "🎉 Você acertou em $tries tentativas! 🎉"
        }

        output.text = tip
        input.text.clear()
    }
}
