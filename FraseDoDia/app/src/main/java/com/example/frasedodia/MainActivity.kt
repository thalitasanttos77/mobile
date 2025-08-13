package com.example.frasedodia

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun quote(view: View){
        val quotes = arrayOf(
            "O sucesso é a soma de pequenos esforços repetidos dia após dia.",
            "A única maneira de fazer um excelente trabalho é amar o que você faz.",
            "Não espere por oportunidades extraordinárias. Agarre as ocasiões comuns e as torne grandes.",
            "Continue. A sua única limitação é a sua mente.",
            "Comece onde você está. Use o que você tem. Faça o que você pode.",
            "Feito é melhor que perfeito.",
            "A persistência realiza o impossível.",
            "O futuro pertence àqueles que acreditam na beleza de seus sonhos.",
            "Nunca é tarde para recomeçar.",
            "Acredite em você mesmo e em tudo que você é. Saiba que existe algo dentro de você que é maior do que qualquer obstáculo."
        )
            val index = (0..9).random()
            val textView = findViewById<TextView>(R.id.textViewOutput)
            textView.text = quotes[index]
    }
}