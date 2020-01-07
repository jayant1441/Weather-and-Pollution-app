package com.example.weatherandpollutionapp.Fragments




import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.weatherandpollutionapp.R
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*



class Weatherfragment : Fragment() {

    var latitude:String = "29.3909"
    var longitude:String = "76.9635"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val my_view =  inflater.inflate(R.layout.fragment_weather, container, false)

        var city_name = my_view.tv_city_name as TextView
        var updated_time= my_view.tv_updated_time as TextView
        var current_temp = my_view.tv_current_temp as TextView
        var min_temp = my_view.tv_min_temp as TextView
        var max_temp = my_view.tv_max_temp as TextView
        var sunrise_time = my_view.tv_sunrise_time as TextView
        var sunset_time = my_view.tv_sunset_time as TextView
        var wind = my_view.tv_wind as TextView
        var pressure = my_view.tv_pressure as TextView
        var humidity = my_view.tv_humidity as TextView

        weatherTask().execute()


        return my_view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherTask().execute()

    }


    inner class weatherTask() : AsyncTask<String, Void, String>() {


        override fun onPreExecute() {

        }
        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=panipat,in&units=metric&appid=2714eacb067ca28bd380613ccbfa55dc").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt:Long = jsonObj.getLong("dt")

                val pressure = main.getString("pressure")
                val temp = main.getString("temp")
                val temp_min = main.getString("temp_min")
                val temp_max = main.getString("temp_max")
                val humidity = main.getString("humidity")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val sunrise = sys.getLong("sunrise")
                val sunset = sys.getLong("sunset")
                val sky_status = weather.getString("description")
                val wind_speed =wind.getString("speed")
                val city_name = jsonObj.getString("name")


                tv_sky_status.text = sky_status
                tv_current_temp.text = temp + "°C"
                tv_pressure.text = pressure
                tv_max_temp.text = "Max Temp: "+temp_max + "°C"
                tv_min_temp.text = "Min Temp: "+temp_min + "°C"
                tv_humidity.text = humidity
                tv_sunrise_time.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                tv_sunset_time.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                tv_wind.text = wind_speed + " Km/h"
                tv_updated_time.text = updatedAtText
                tv_city_name.text = city_name


            }catch (e:Exception){
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }



        }

    }

}