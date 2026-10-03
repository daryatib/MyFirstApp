package com.example.myfirstapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим элементы по их ID
        val editText = findViewById<EditText>(R.id.editTextText)
        val btnOpenSecond = findViewById<Button>(R.id.buttonOpenSecond)
        val btnCall = findViewById<Button>(R.id.buttonCall)
        val btnShare = findViewById<Button>(R.id.buttonShare)

        // 1. Явный интент: Открыть вторую Activity и передать текст
        btnOpenSecond.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EXTRA_TEXT", text) // Передаем данные с ключом "EXTRA_TEXT"
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Неявный интент: Совершить звонок
        btnCall.setOnClickListener {
            val phoneNumber = editText.text.toString()
            if (phoneNumber.isNotEmpty()) {
                // Используем ACTION_DIAL, чтобы открыть приложение звонилки с номером
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите номер телефона!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Системный интент: Поделиться текстом
        btnShare.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                // Используем ACTION_SEND для отправки текста в другие приложения
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                // createChooser покажет системное окно выбора приложения
                startActivity(Intent.createChooser(intent, "Поделиться через..."))
            } else {
                Toast.makeText(this, "Введите текст для отправки!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}