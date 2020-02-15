package com.example.dasapp2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 15) {
            val policy =
                StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val login: Button = findViewById<Button>(R.id.login)
        val register: Button = findViewById<Button>(R.id.register)

        login.setOnClickListener{
            val loginintent = Intent(this,LoginActivity::class.java)
            startActivity(loginintent)
        }

        register.setOnClickListener{
            val registerintent = Intent(this,RegisterActivity::class.java)
            startActivity(registerintent)
        }
    }
}
