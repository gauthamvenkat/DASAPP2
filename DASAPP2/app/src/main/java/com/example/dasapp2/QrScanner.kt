package com.example.dasapp2

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.qr_scanner.*


class QrScanner: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_scanner)
        var Res : String
        btn_scan.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                //if (result.contents == null)
                //{
                //qrScan()
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                //} else
                //{
                faceReg()
                //var res:String
                //res = result.contents.toString()
                //System.out.println(res)
                //}
            }
            else
            {
                qrScan()
                //super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
    private fun faceReg()
    {
        val intent = Intent(this, FaceRecognition::class.java)
        startActivity(intent)
    }
    private fun qrScan()
    {
        val intent = Intent(this, QrScanner::class.java)
        startActivity(intent)
    }
}