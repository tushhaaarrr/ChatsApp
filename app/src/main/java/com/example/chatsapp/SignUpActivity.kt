package com.example.chatsapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupHome :TextView
    private lateinit var signupName : EditText
    private lateinit var signupEmail : EditText
    private lateinit var signupPassword : EditText
    private lateinit var confirmPassword : EditText
    private lateinit var btnNext : ImageView
    private lateinit var signupToLogin : TextView
    private lateinit var mDbRef : DatabaseReference


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
        confirmPassword = findViewById(R.id.confirmPassword)
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
            val confirmPassword = confirmPassword.text.toString()

            signup(name, email, password, confirmPassword)
        }
    }



    private fun signup( name: String, email: String, password: String, confirmPassword : String) {
        //sign up users

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }


        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign up fails
                    Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show()

                }
            }



    }

    private fun addUserToDatabase(name: String, email: String, uid : String){

        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name, email, uid))

    }
}