package com.sandeep.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvScore: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvScore = findViewById(R.id.tvScore)

        btnCalculate.setOnClickListener {
            if (etWeight.text.isBlank() || etHeight.text.isBlank()) {
                Toast.makeText(this, "Please enter weight and height.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = etWeight.text.toString().toFloat()
            val height = etHeight.text.toString().toFloat()
            val bmiScore = weight / ((height / 100) * (height / 100))
            val bmi2Digits = String.format("%.2f", bmiScore)
            display(bmi2Digits.toFloat())
//            tvScore.text = bmi2Digits
        }
    }

    private fun display(bmi:Float){
        var tvDescription = findViewById<TextView>(R.id.tvDescription)
        var tvInfo = findViewById<TextView>(R.id.tvInfo)
        tvScore.text = bmi.toString()
//        tvDescription.text = "You are healthy."
        tvInfo.text = "(Normal range is 18.5 - 24.9)"
        var text = ""
        var color = 0
        when{
            bmi < 18.5 -> {
                text = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.99 -> {
                text = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                text = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                text = "Obese"
                color = R.color.obese
            }
        }
        tvDescription.setTextColor(ContextCompat.getColor(this, color))
        tvDescription.text = text

    }


}