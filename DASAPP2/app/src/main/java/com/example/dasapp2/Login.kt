package com.example.dasapp2

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Login(var loginres:String) {
    class Deserializer: ResponseDeserializable<Array<Login>>{
        override fun deserialize(content: String): Array<Login>? = Gson().fromJson(content, Array<Login>::class.java)
    }
}