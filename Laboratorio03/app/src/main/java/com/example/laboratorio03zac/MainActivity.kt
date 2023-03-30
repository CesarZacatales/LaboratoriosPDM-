package com.example.laboratorio03zac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var count = 0.00
    private lateinit var countMoney: TextView

    private lateinit var clickFive: ImageView
    private lateinit var clickTen: ImageView
    private lateinit var clickQuarter: ImageView
    private lateinit var clickOneDollar: ImageView

    private lateinit var displayFive: TextView
    private lateinit var displayTen: TextView
    private lateinit var displayQuarter: TextView
    private lateinit var displayOneDollar: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bin()
        onListenerFun()
    }

    fun bin() {
        clickFive = findViewById(R.id.five_ctvs_image)
        clickTen = findViewById(R.id.ten_ctvs_image)
        clickQuarter = findViewById(R.id.quarter_image)
        clickOneDollar = findViewById(R.id.one_dollar_image)
        displayFive = findViewById(R.id.five_ctvs_text)
        displayTen = findViewById(R.id.ten_ctvs_text)
        displayQuarter = findViewById(R.id.quarter_text)
        displayOneDollar = findViewById(R.id.one_dollar_text)
        countMoney = findViewById(R.id.count_text)
    }

    fun onListenerFive(){
        clickFive.setOnClickListener{
            count += displayFive.text.toString().toFloat()
            countMoney.setText("$"+String.format("%.2f", count).toFloat().toString())}
    }

    fun onListenerTen(){
        clickTen.setOnClickListener{
            count += displayTen.text.toString().toFloat()
            countMoney.setText("$"+String.format("%.2f", count).toFloat().toString())}
    }

    fun onListenerQuarter(){
        clickQuarter.setOnClickListener{
            count += displayQuarter.text.toString().toFloat()
            countMoney.setText("$"+String.format("%.2f", count).toFloat().toString())}
    }

    fun onListenerOneDollar(){
        clickOneDollar.setOnClickListener{
            count += displayOneDollar.text.toString().toFloat()
            countMoney.setText("$"+String.format("%.2f", count).toFloat().toString())}
    }

    fun onListenerFun(){
        onListenerFive()
        onListenerTen()
        onListenerQuarter()
        onListenerOneDollar()
    }
}