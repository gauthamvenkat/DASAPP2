package com.example.dasapp2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Body
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class LoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    //private var PRIVATE_MODE= 0
    //private val PREF_NAME = "welcome"
    private val displaymessage by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (Build.VERSION.SDK_INT >= 15) {
            val policy =
                StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        var url = "http://das.pythonanywhere.com/login_mobile"
        val url1 = "http://192.168.0.109/login_mobile"
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)


        val login: Button = findViewById<Button>(R.id.login)
        val jsonobj = JSONObject()
        val jsonobj1 = JSONObject()
        val jsonobj2 = JSONObject()
        login.setOnClickListener {
            /*if(username.text.toString().equals("ad")&& password.text.toString().equals("p"))
            {
                val intent = Intent(this, FaceRecognition::class.java)
                startActivity(intent)
            }
            else if(username.text.toString().isEmpty() && password.text.toString().isEmpty())
            {
                displaymessage.text = "Please Check if username or password is entered"
            }
            else if(username.text.toString()!= "ad" && password.text.toString()!= "p")
            {
                displaymessage.text = "                 Wrong Username or Password"
            }*/
            val user = username.text.toString().trim()
            val password = password.text.toString().trim()
            val user1 = "f1"
            val pass = "password"
            jsonobj.put("username", user1)
            jsonobj.put("password", pass)
            Fuel.post("https://das.pythonanywhere.com/login_mobile", listOf("username" to "$user", "password" to "$password"))
                    .response{ request, response, result ->
                        println(response)
                        println("Lol")
                        println("Gautham")
                        println(result)
                }
            /*Fuel.post(url)
                .jsonBody(jsonobj.toString())
                .also { println(it) }
                .response { request, response, result ->
                    println(request)
                    println(response)
                    val (bytes, error) = result
                    if (bytes != null) {
                        println("[response bytes] ${String(bytes)}")
                    }*/
                    /*jsonobj1.put("login","failed")
                    jsonobj2.put("login","success")
                    val Req = bytes.toString()
                    if(response.equals(Req))
                    {
                        val faceintent = Intent(this,FaceRecognition::class.java)
                        startActivity(faceintent)
                    }
                    else
                    {
                        Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show()
                    }*/
                }


        }

        //val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        //if(sharedPref.getBoolean(PREF_NAME,false)){
        //val faceIntent = Intent(this, FaceRecognition::class.java)
        //startActivity(faceIntent)
        //finish()
        //}
        //else{
        //val homeIntent = Intent(this, MainActivity::class.java)
        //startActivity(homeIntent)
        //finish()
        //}
    }


