package com.example.weatherandpollutionapp.Fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.weatherandpollutionapp.MainActivity
import com.example.weatherandpollutionapp.SignUp_Login.LoginActivity2
import com.example.weatherandpollutionapp.R
import com.example.weatherandpollutionapp.developer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*


class SettingsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance()
        val  my_view4=  inflater.inflate(R.layout.fragment_settings, container, false)
        val tv_login = my_view4.tv_login as TextView
        val tv_log_out = my_view4.tv_log_out as TextView
        val tv_delete_account = my_view4.tv_delete_Account as TextView
        val tv_contact_us = my_view4.tv_contact_us as TextView
        val tv_developer = my_view4.tv_developer as TextView


        tv_delete_account.setOnClickListener{
            val user = FirebaseAuth.getInstance().currentUser

            user?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context,"Account Deleted Successfully" , Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Some error occured" , Toast.LENGTH_SHORT).show()
                    }
            }
        }


        tv_log_out.setOnClickListener{
            if(onStart().equals(true)){
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context,"User Signed Out" , Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Login First" , Toast.LENGTH_SHORT).show()
            }
        }



        tv_login.setOnClickListener {
            if (onStart().equals(true)){
                println("already logged in")
            }
            else{
                val intent_to_login_activity = Intent(context, LoginActivity2::class.java)
                startActivity(intent_to_login_activity)
            }
        }


        tv_contact_us.setOnClickListener {
            val intent_to_gmail = Intent(Intent.ACTION_SENDTO)
            intent_to_gmail.data = Uri.parse("mailto:")
            intent_to_gmail.putExtra(Intent.EXTRA_EMAIL,"jayantdhingra1441@gmail.com")
            intent_to_gmail.putExtra(Intent.EXTRA_SUBJECT,"Feedback")
            startActivity(intent_to_gmail)
        }

        tv_developer.setOnClickListener {
            var intent_to_developer = Intent(context,developer::class.java)
            startActivity(intent_to_developer)
        }


        return my_view4


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login_to_welcome()
    }


    private fun login_to_welcome(){
        if(onStart().equals(true)){
            tv_login.text = "Welcome"
            tv_login.isEnabled = false
        }
        else{
            tv_login.text = "Login"
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }




}