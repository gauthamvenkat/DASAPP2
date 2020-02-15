package com.example.dasapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

class FaceRecognition : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.face_recognition)
        val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this

        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                faceReg()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                callMe()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                faceReg()
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Give your Biometrics inorder to Continue").setSubtitle("").setDescription("").setNegativeButtonText("Cancel").build()

        findViewById<Button>(R.id.face).setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun callMe()
    {
        val intent = Intent(this, QrScanner::class.java)
        startActivity(intent)
    }
    private fun faceReg()
    {
        val intent = Intent(this, FaceRecognition::class.java)
        startActivity(intent)
    }
}
