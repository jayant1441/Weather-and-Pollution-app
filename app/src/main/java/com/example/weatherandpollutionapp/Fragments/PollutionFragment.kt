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
import kotlinx.android.synthetic.main.fragment_pollution.*
import kotlinx.android.synthetic.main.fragment_pollution.view.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class PollutionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var my_view2 = inflater.inflate(R.layout.fragment_pollution, container, false)

        var tv_pollutiondisplay = my_view2.tv_pollutionDisplay

        pollutionTask().execute()
        return my_view2
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pollutionTask().execute()

    }

    inner class pollutionTask() : AsyncTask<String, Void, String>() {


        override fun onPreExecute() {

        }
        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.airvisual.com/v2/nearest_city?lat=29.3909&lon=76.9635&key=4f82e139-1602-4bb8-b1c0-62d0b22bd293").readText(
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
                val jsonObj2 = JSONObject(result)
             /**   val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt:Long = jsonObj.getLong("dt")

                val pressure = main.getString("pressure")
                val temp = main.getString("temp")
                val temp_min = main.getString("temp_min")
                val temp_max = main.getString("temp_max")
                val humidity = main.getString("humidity")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000)
                )
                val sunrise = sys.getLong("sunrise")
                val sunset = sys.getLong("sunset")
                val sky_status = weather.getString("description")
                val wind_speed =wind.getString("speed")
                val city_name = jsonObj.getString("name")**/


                val data = jsonObj2.getJSONObject("data")
                val current = data.getJSONObject("current")
                val pollution = current.getJSONObject("pollution")

                val city = data.getString("city")
                val aqius = pollution.getString("aqius")


                tv_pollutionDisplay.text = aqius
                tv_city.text = city




            }catch (e:Exception){
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }



        }

    }

}