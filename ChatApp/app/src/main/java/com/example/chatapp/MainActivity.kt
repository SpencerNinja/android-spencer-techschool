package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Input fields for email and password
        var email = findViewById<AppCompatEditText>(R.id.et_email)
        var password = findViewById<AppCompatEditText>(R.id.et_password)

        // Login Button
        var loginBtn = findViewById<AppCompatButton>(R.id.btn_sign_in)
        loginBtn.setOnClickListener {
            if(email.text?.length == 0 || password.text?.length == 0){
                Toast.makeText(
                    baseContext, "Enter valid details",
                    Toast.LENGTH_SHORT
                ).show()
            }else if(password.text?.length!! < 8){
                Toast.makeText(
                    baseContext, "Password must be at least 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                signInUser(email.text.toString(),password.text.toString())
            }
        }

        // Signup Button
        var signupBtn = findViewById<AppCompatButton>(R.id.btn_sign_up)
        signupBtn.setOnClickListener {
            if(email.text?.length == 0 || password.text?.length == 0){
                Toast.makeText(
                    baseContext, "Enter valid details",
                    Toast.LENGTH_SHORT
                ).show()
            }else if(password.text?.length!! < 8){
                Toast.makeText(
                    baseContext, "Password must be at least 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                createNewUser(email.text.toString(), password.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
        val currentUser = null
        if (currentUser != null) {
            Toast.makeText(
                baseContext, "Already Logged In.",
                Toast.LENGTH_SHORT
            ).show()
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext, "Authentication successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startHomeActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email.trim(), password.trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext, "Authentication successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startHomeActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signInWithCustomToken(customToken: String) {
        customToken?.let {
            auth.signInWithCustomToken(it)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    
}