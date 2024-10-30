package com.example.numberapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.numberlist.R
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber: EditText = findViewById(R.id.editTextNumber)
        val btnShow: Button = findViewById(R.id.btnShow)
        val listView: ListView = findViewById(R.id.listView)
        val errorText: TextView = findViewById(R.id.errorText)
        val radioEven: RadioButton = findViewById(R.id.radioEven)
        val radioOdd: RadioButton = findViewById(R.id.radioOdd)
        val radioSquare: RadioButton = findViewById(R.id.radioSquare)

        btnShow.setOnClickListener {
            val inputText = editTextNumber.text.toString()
            val inputNumber = inputText.toIntOrNull()

            if (inputNumber != null && inputNumber >= 0) {
                errorText.visibility = TextView.GONE

                val numbers = when {
                    radioEven.isChecked -> getEvenNumbers(inputNumber)
                    radioOdd.isChecked -> getOddNumbers(inputNumber)
                    radioSquare.isChecked -> getSquareNumbers(inputNumber)
                    else -> listOf()
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
                listView.adapter = adapter
            } else {
                errorText.text = "Please enter a valid positive integer"
                errorText.visibility = TextView.VISIBLE
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { sqrt(it.toDouble()).toInt() * sqrt(it.toDouble()).toInt() == it }
    }
}
