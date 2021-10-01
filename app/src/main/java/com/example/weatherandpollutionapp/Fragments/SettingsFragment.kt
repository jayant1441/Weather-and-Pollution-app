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
import com.example.weatherandpollutionapp.ChangePassword
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
        val tv_about_us = my_view4.tv_about_us as TextView
        val tv_change_password = my_view4.tv_change_password as TextView


        tv_delete_account.setOnClickListener{
            val user = FirebaseAuth.getInstance().currentUser

            user?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context,"Account Deleted" , Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error Occured" , Toast.LENGTH_SHORT).show()
                    }
            }
        }


        val user = FirebaseAuth.getInstance().currentUser
        tv_log_out.setOnClickListener {

            if (user != null) {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context,"Logged Out" , Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context,"Please Login" , Toast.LENGTH_SHORT).show()

            }
        }



        tv_login.setOnClickListener {
            if(user == null){
                val intent_to_login_activity = Intent(context, LoginActivity2::class.java)
                startActivity(intent_to_login_activity)
            }
            else
            {
                Toast.makeText(context,"Already logged in" , Toast.LENGTH_SHORT).show()

            }

        }


        tv_contact_us.setOnClickListener {
            val intent_to_gmail = Intent(Intent.ACTION_SENDTO)
            intent_to_gmail.data = Uri.parse("mailto:")
            intent_to_gmail.putExtra(Intent.EXTRA_EMAIL,"jayantdhingra1441@gmail.com")
            intent_to_gmail.putExtra(Intent.EXTRA_SUBJECT,"Have something on your mind")
            startActivity(intent_to_gmail)
        }

        tv_developer.setOnClickListener {
            var intent_to_developer = Intent(context,developer::class.java)
            startActivity(intent_to_developer)
        }

        tv_about_us.setOnClickListener {
            var intent_to_developer = Intent(context,developer::class.java)
            startActivity(intent_to_developer)
        }

        tv_change_password.setOnClickListener {
            startActivity(Intent(context,ChangePassword::class.java))
        }


        return my_view4


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login_to_welcome()
    }


    private fun login_to_welcome(){
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null ) {
            tv_login.text = "Greetings"
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }




}
