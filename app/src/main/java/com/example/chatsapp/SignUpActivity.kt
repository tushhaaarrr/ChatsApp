package com.example.chatsapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupHome :TextView
    private lateinit var signupName : EditText
    private lateinit var signupEmail : EditText
    private lateinit var signupPassword : EditText
    private lateinit var btnNext : ImageView
    private lateinit var signupToLogin : TextView

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        signupHome = findViewById(R.id.signupHome)
        signupName = findViewById(R.id.signupName)
        signupEmail = findViewById(R.id.signupEmail)
        signupPassword = findViewById(R.id.signupPassword)
        btnNext = findViewById(R.id.next)
        signupToLogin = findViewById(R.id.SignUpToLogin)




        signupHome.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

        signupToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener {

            val name = signupName.text.toString()
            val email = signupEmail.text.toString()
            val password = signupPassword.text.toString()

            signup(name, email, password)
        }
    }



    private fun signup( name: String, email: String, password: String) {
        //sign up users

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Toast.makeText(this,"$name, your account has been created successfully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails
                    Toast.makeText(this, " $name, an error occurred during your registration. Please check your entries.",Toast.LENGTH_SHORT).show()

                }
            }



    }
}