package com.example.weatherandpollutionapp.SignUp_Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.weatherandpollutionapp.MainActivity
import com.example.weatherandpollutionapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)


        auth = FirebaseAuth.getInstance()

        btn_login2.setOnClickListener {
            try {
                LoginUser()

                auth.signInWithEmailAndPassword(et_email_login2.text.toString(), et_password_login2.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(baseContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }



        btn_create_account2.setOnClickListener {
            val intent_signup = Intent(this, SignUp_User::class.java)
            startActivity(intent_signup)
            this.finish()
        }


    }

    private fun LoginUser() {
        if (et_email_login2.text.isEmpty()) {
            et_email_login2.error = "Please enter your email"
            et_email_login2.requestFocus()
        }
        if (et_password_login2.text.isEmpty()) {
            et_password_login2.error = "Please enter Password"

            et_password_login2.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_email_login2.text.toString()).matches()) {
            et_email_login2.error = "Enter correct email"
            et_email_login2.requestFocus()
            return
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val intent_to_MainActivity = Intent(this, MainActivity::class.java)
            startActivity(intent_to_MainActivity)
            this.finish()


        } else {
            println("login failed")
        }

    }

}

