package com.example.weatherandpollutionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {

    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btn_confirm.setOnClickListener {

            if (!et_currentPassword.text.isEmpty() && !et_confirm_password.text.isEmpty() && !et_new_password.text.isEmpty()) {
                val credential = EmailAuthProvider
                    .getCredential(user!!.email!!, et_currentPassword.text.toString())

// Prompt the user to re-provide their sign-in credentials

                if (et_confirm_password.text.toString() == et_new_password.text.toString()) {
                    user?.reauthenticate(credential)
                        ?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                val newPassword = et_confirm_password.text.toString()

                                user?.updatePassword(newPassword)
                                    ?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                baseContext,
                                                "Password Successfully changed",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            this.finish()
                                        } else {
                                            Toast.makeText(
                                                baseContext,
                                                "Password cannot be changed",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            } else {

                            }
                        }

                } else {
                    Toast.makeText(baseContext, "Fields doesn't match", Toast.LENGTH_SHORT).show()

                }

            } else {
                Toast.makeText(baseContext, "Fill all Fields", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
