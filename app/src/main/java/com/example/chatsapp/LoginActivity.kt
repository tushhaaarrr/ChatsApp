package com.example.chatsapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginHome : TextView
    private lateinit var etLoginEmail : EditText
    private lateinit var etLoginpassword : EditText
    private lateinit var btnNext : ImageView
    private lateinit var loginToSignUp : TextView
    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()



        loginHome = findViewById(R.id.signupHome)
        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginpassword = findViewById(R.id.etLoginPassword)
        btnNext = findViewById(R.id.btnNext)
        loginToSignUp = findViewById(R.id.loginToSignUp)


        loginHome.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        loginToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{

            val email = etLoginEmail.text.toString()
            val password = etLoginEmail.text.toString()

            login(email,password)
        }


    }

    private fun login(email: String, password: String) {
        //Login user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails
                    Toast.makeText(this,"Please check your entries!!", Toast.LENGTH_SHORT).show()


                }
            }


    }
}