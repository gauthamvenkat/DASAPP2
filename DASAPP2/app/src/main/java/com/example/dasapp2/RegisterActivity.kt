package com.example.dasapp2

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.extensions.jsonBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.login_activity.textView
import kotlinx.android.synthetic.main.register_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Retrofit
import io.reactivex.Observable
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class RegisterActivity : AppCompatActivity() {
    private lateinit var id: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val url = "http://das.pythonanywhere.com/register_mobile"
        val url1 = "http://127.0.0.1:8000/register"
        id = findViewById(R.id.id)
        name = findViewById(R.id.name)
        password = findViewById(R.id.password)
        email = findViewById(R.id.email)
        textView = findViewById(R.id.textView)



        val register: Button = findViewById<Button>(R.id.register)
        val jsonobj = JSONObject()
        register.setOnClickListener {
            var regno = id.text.toString()
            var name = name.text.toString()
            var password = password.text.toString()
            var email = email.text.toString()

            /*jsonobj.put("id",regno)
            jsonobj.put("name",name)
            jsonobj.put("password",password)
            jsonobj.put("email",email)
            jsonobj.put("cat","Student")*/

            //Content type needs to be changed.
            /*Fuel.patch(url).header(Headers.CONTENT_TYPE,"application/x-www-form-urlencoded")
                .jsonBody(jsonobj.toString())
                .also { println(it) }
                .response { request,response,result ->
                    println(request)
                    println(response)
                    val (bytes, error) = result
                    if (bytes != null) {
                        println("[response bytes] ${String(bytes)}")
                    }
                }*/
            val url = URL(url)
            val params: MutableMap<String, Any> = LinkedHashMap()
            params["id"] = regno
            params["name"] = name
            params["password"] = password
            params["email"] = email
            params["cat"] = "Student"
            val postData = StringBuilder()
            for ((key, value) in params) {
                if (postData.length != 0) postData.append('&')
                postData.append(URLEncoder.encode(key, "UTF-8"))
                postData.append('=')
                postData.append(URLEncoder.encode(value.toString(), "UTF-8"))
            }
            val postDataBytes = postData.toString().toByteArray(charset("UTF-8"))

            val conn =
                url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            conn.setRequestProperty("Content-Length", postDataBytes.size.toString())
            conn.doOutput = true
            conn.outputStream.write(postDataBytes)
            /*val bodyJson = """
                            { "id" : "$regno",
                              "name" : "$name",
                              "password" : "$password",
                              "email" : "$email",
                              "cat" : "Student"
                            }
                           """
            System.out.println(bodyJson)
            Fuel.post("$url").body(bodyJson).response{ request, response, result ->
                println(request)
                println(response)
                val (bytes, error) = result
                if (bytes != null) {
                    println("[response bytes] ${String(bytes)}")
                }

            }*/
            /*val request = JsonObjectRequest(
                Request.Method.POST,url,jsonobj,
                Response.Listener { response ->
                    // Process the json
                    try {
                        textView.text = "Response: $response"
                    }catch (e:Exception){
                        textView.text = "Exception: $e"
                    }

                }, Response.ErrorListener{
                    // Error in request
                    textView.text = "Volley error: $it"
                })
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            VolleySingleton.getInstance(this).addToRequestQueue(request)*/
        }
    }
    /*public fun send(view: View) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        // clear text result
        textView.setText("")

        if (checkNetworkConnection())
            lifecycleScope.launch {
                val result = httpPost("http://das.pythonanywhere.com/register_mobile")
                textView.setText(result)
            }
        else
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show()

    }

    @Throws(IOException::class, JSONException::class)
    private suspend fun httpPost(myUrl: String): String {

        val result = withContext(Dispatchers.IO) {
            val url = URL(myUrl)
            // 1. create HttpURLConnection
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            // 2. build JSON object
            val jsonObject = buidJsonObject()

            // 3. add JSON content to POST request body
            setPostRequestContent(conn, jsonObject)

            // 4. make POST request to the given URL
            conn.connect()

            // 5. return response message
            conn.responseMessage + ""
        }
        return result
    }

    private fun checkNetworkConnection(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo
        val isConnected: Boolean = if(networkInfo != null) networkInfo.isConnected() else false
        if (networkInfo != null && isConnected) {
            // show "Connected" & type of network "WIFI or MOBILE"
            textView.text = "Connected " + networkInfo.typeName
            // change background color to red
        } else {
            // show "Not Connected"
            textView.text = "Not Connected"
            // change background color to green
            textView.setBackgroundColor(-0x10000)
        }
        return isConnected
    }

    @Throws(JSONException::class)
    private fun buidJsonObject(): JSONObject {

        val jsonObject = JSONObject()
        jsonObject.accumulate("id", id.getText().toString())
        jsonObject.accumulate("name", name.getText().toString())
        jsonObject.accumulate("email", email.getText().toString())
        jsonObject.accumulate("password", password.getText().toString())
        jsonObject.accumulate("cat", "Student")
        return jsonObject
    }

    @Throws(IOException::class)
    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {

        val os = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject.toString())
        Log.i(MainActivity::class.java.toString(), jsonObject.toString())
        writer.flush()
        writer.close()
        os.close()
    }*/
}
