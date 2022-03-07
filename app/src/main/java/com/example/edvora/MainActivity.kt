package com.example.edvora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val URLstring = "https://assessment.api.vweb.app/rides"

    val ID =ArrayList<String>()
    val originStationCode =ArrayList<String>()
    val stationPath =ArrayList<String>()
    val destinationStationCode =ArrayList<String>()
    val date =ArrayList<String>()
    val mapUrl =ArrayList<String>()
    val state =ArrayList<String>()
    val city =ArrayList<String>()



    lateinit var madapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val action = supportActionBar
//        action!!.title = "Edvora"

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        requestJSON();

        madapter = DataAdapter(ID,originStationCode,stationPath,destinationStationCode,date,mapUrl,state,city)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = madapter


//        val intent = Intent(this,NearestActivity::class.java)
//        //intent.putExtra("count",count.toString())
//        startActivity(intent)



    }

    private fun requestJSON() {

        val stringRequest = StringRequest(
            Request.Method.GET, URLstring,
            { response ->
                Log.d("strrrrr", ">>$response")

                try {
                    //getting the whole json object from the response
                    val JSONarray = JSONArray(response)

                    for (i in 0 until JSONarray.length()){
                        val row = JSONarray.getJSONObject(i)

                        ID.add(row.getString("id"))
                        originStationCode.add(row.getString("origin_station_code"))
                        stationPath.add(row.getString("station_path"))
                        destinationStationCode.add(row.getString("destination_station_code"))
                        date.add(row.getString("date"))
                        mapUrl.add(row.getString("map_url"))
                        state.add(row.getString("state"))
                        city.add(row.getString("city"))

//                        Log.d("ID:","$ID")
                    }

                    madapter.notifyDataSetChanged()



                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            },
            { error ->
                //displaying the error in toast if occurrs
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            })

        // request queue
        val requestQueue = Volley.newRequestQueue(this)

        requestQueue.add(stringRequest)

    }

}