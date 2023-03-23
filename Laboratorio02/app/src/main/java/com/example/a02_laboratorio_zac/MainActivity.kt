package com.example.a02_laboratorio_zac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mDisplayNameTextView: TextView
    private lateinit var mNameEditText: EditText
    private lateinit var mSendButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()

        mDisplayNameTextView.text=""
        mSendButton.setOnClickListener {
            val name = mNameEditText.text.toString()
            mDisplayNameTextView.text = getString(R.string.greeting_text, name)
        }
    }



    fun bind(){
        mNameEditText = findViewById(R.id.edit_text)
        mDisplayNameTextView = findViewById(R.id.greeting_text)
        mSendButton = findViewById(R.id.action_send)
    }
}



