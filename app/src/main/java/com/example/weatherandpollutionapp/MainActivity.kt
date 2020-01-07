package com.example.weatherandpollutionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherandpollutionapp.Fragments.MapsFragment
import com.example.weatherandpollutionapp.Fragments.PollutionFragment
import com.example.weatherandpollutionapp.Fragments.SettingsFragment
import com.example.weatherandpollutionapp.Fragments.Weatherfragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        BottomNavigationView.setOnNavigationItemSelectedListener {items ->
            when(items.itemId){
                R.id.weather_navigation -> {fragmentManager(Weatherfragment())

                    return@setOnNavigationItemSelectedListener true}
                R.id.Pollution_navigation->{fragmentManager(PollutionFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Maps_navigation -> {fragmentManager(MapsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Settings_navigation->{fragmentManager(SettingsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        fragmentManager(Weatherfragment())


    }

    private fun fragmentManager(FragmentName:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.Fragment_container,FragmentName)
        fragmentTransaction.commit()

    }
}
