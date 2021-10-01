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

                val data = jsonObj2.getJSONObject("data")
                val current = data.getJSONObject("current")
                val pollution = current.getJSONObject("pollution")

                val city = data.getString("city")
                val aqius = pollution.getString("aqius")


                tv_pollutionDisplay.text = aqius
                tv_city.text = city
                textView4.text = "Air Quality Index (AQI)"


            }catch (e:Exception){
                Toast.makeText(context, "Whoops! Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
