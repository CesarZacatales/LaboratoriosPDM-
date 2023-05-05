package com.example.a02_laboratorio_zac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var Height: EditText
    private lateinit var Weight: EditText
    private lateinit var SendButton: Button
    private lateinit var BmiResult: TextView
    private lateinit var ResultBmi:TextView
    private lateinit var ConsejoBmi: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bin()
        set()
    }
    fun bin(){
        SendButton = findViewById(R.id.btnCalculate)
        Height = findViewById(R.id.editTextHeight)
        Weight = findViewById(R.id.editTextWeight)
        BmiResult = findViewById(R.id.textViewTitleResult)
        ResultBmi = findViewById(R.id.textViewResultText2)
        ConsejoBmi = findViewById(R.id.textViewResultText3)
    }

    fun set(){
    SendButton.setOnClickListener{
        val weight = Weight.text.toString()
        val height = Height.text.toString()
        if(!validator(weight, height)){
            clearResult()
            return@setOnClickListener
        }
        Weight.setText("")
        Height.setText("")
        val bmi = calculate(weight.toFloat(), height.toFloat())
        val bmiTwoDigits = String.format("%.2f", bmi).toFloat()
        result(bmiTwoDigits)
    }
    }
    private fun validator(weight: String?, height: String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> true
        }
    }

    private fun calculate(weight: Float, height: Float): Float = weight/ ( height* height)

    private fun result(bmi:Float){
        BmiResult.text = bmi.toString()
        ResultBmi.text = "(normal range is 18.5 - 24.9)"
        var infoResult = ""
        var color = 0

        when{
            bmi < 18.50 -> {
                infoResult = "under weight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                infoResult = "healthy"
                color = R.color.normal_weight
            }
            bmi in 24.99..29.99 ->{
                infoResult = "over weight"
                color = R.color.over_weight
            }
            bmi >29.99 -> {
                infoResult = "Obese"
                color = R.color.obese
            }
        }
        ConsejoBmi.setTextColor(ContextCompat.getColor(this, color))
        ConsejoBmi.text = infoResult
    }

    private fun clearResult(){
        BmiResult.text = ""
        ResultBmi.text = ""
        ConsejoBmi.text = ""
    }
}




