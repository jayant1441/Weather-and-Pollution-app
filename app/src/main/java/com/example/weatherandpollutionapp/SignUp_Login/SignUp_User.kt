package com.example.weatherandpollutionapp.SignUp_Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.weatherandpollutionapp.MainActivity
import com.example.weatherandpollutionapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up__user.*
import java.lang.Exception


class SignUp_User : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__user)

        auth = FirebaseAuth.getInstance()


        btn_signUp.setOnClickListener {
            try{signupUser()
            auth.createUserWithEmailAndPassword(et_email.text.toString(), et_password.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Signup successfully", Toast.LENGTH_SHORT).show()
                    val intent_MainActivity = Intent(this,
                        MainActivity::class.java)
                    startActivity(intent_MainActivity)
                } else {

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }

            }}catch (ex:Exception){
                Toast.makeText(baseContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }


        }

        tv_already_have_an_account.setOnClickListener{
            val intent_to_login_activity = Intent(this,
                LoginActivity2::class.java)
            startActivity(intent_to_login_activity)
            this.finish()
        }



    }

    private fun signupUser(){
        if(et_email.text.isEmpty()) {
            et_email.error = "Please enter your email"
            et_email.requestFocus()
        }
        if(et_password.text.isEmpty()){
            et_password.error = "Please enter Password"
            et_password.requestFocus()
        }
        if(et_name.text.isEmpty()){
            et_name.error = "Please enter your name"
            et_name.requestFocus()
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches()) {
            et_email.error = "Enter correct email"
            et_email.requestFocus()
            return
        }
    }
}











