package com.laine.mauro.simplebiometricprompt

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verification_btn.setOnClickListener {
            biometricVerification()
        }
    }

    fun biometricVerification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val executor = mainExecutor
            val cancelListener = DialogInterface.OnClickListener { dialog, which -> }
            val prompt = BiometricPrompt.Builder(this)
                .setTitle("Verify Identity")
                .setSubtitle("Minimal implementation")
                .setDescription("Biometric verification")
                .setNegativeButton("Cancel", executor, cancelListener)
                .build()

            prompt.authenticate(CancellationSignal(), mainExecutor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    Toast.makeText(applicationContext, "You are in!", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "API version lower than P", Toast.LENGTH_LONG).show()
        }
    }
}
