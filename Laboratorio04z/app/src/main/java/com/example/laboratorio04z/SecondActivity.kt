package com.example.laboratorio04z

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var newName:TextView
    private lateinit var newEmail: TextView
    private lateinit var newPhone: TextView
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        newName = findViewById(R.id.nombreTextView)
        newEmail = findViewById(R.id.correoTextview)
        newPhone = findViewById(R.id.telefonoTextView)
        sendButton = findViewById(R.id.sendButton)

        val nameKey = intent.getStringExtra("nombre").toString()
        val emailKey = intent.getStringExtra("correo").toString()
        val phoneKey = intent.getStringExtra("telefono").toString()

        newName.setText(nameKey)
        newEmail.setText(emailKey)
        newPhone.setText(phoneKey)

        sendButton.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Nombre: " + nameKey + "\r"+"Correo electronico: " + emailKey + "\r"+"Telefono: " + phoneKey + "\r" )
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

    }
}
