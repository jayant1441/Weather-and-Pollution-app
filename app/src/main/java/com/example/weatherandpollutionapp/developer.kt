package com.example.weatherandpollutionapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_developer.*

class developer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        fun intent_developer_contact(contact_address: String) {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(contact_address))
            startActivity(intent)
        }


        facebook_contact2.setOnClickListener {
            intent_developer_contact("https://www.facebook.com/jayant.dhingra.54")
        }
        instagram_contact2.setOnClickListener {
            intent_developer_contact("https://www.instagram.com/jayantdhingra3/")
        }
        whatsapp_contact2.setOnClickListener {
            intent_developer_contact("https://api.whatsapp.com/send?phone=918950812364&text=Hey")
        }
        linkedin_contact2.setOnClickListener {
            intent_developer_contact("https://in.linkedin.com/in/jayant-dhingra-30a246195?trk=people-guest_profile-result-card_result-card_full-click")
        }
        gmail_contact2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            // below line tell that data is handled by only mailing apps
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, "jayantdhingra1441@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(intent)
        }
        github_contact2.setOnClickListener {
            intent_developer_contact("https://github.com/jayant1441/")
        }

    }
}
