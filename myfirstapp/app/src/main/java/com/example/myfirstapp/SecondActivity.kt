package com.example.myfirstapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Находим TextView
        val textView = findViewById<TextView>(R.id.textViewResult)

        // Получаем данные из интента по ключу "EXTRA_TEXT"
        val receivedText = intent.getStringExtra("EXTRA_TEXT")

        // Если текст был передан, отображаем его
        if (receivedText != null) {
            textView.text = receivedText
        }
    }
}