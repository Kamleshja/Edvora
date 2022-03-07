package com.example.edvora

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Build.ID
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso

class DataAdapter(
    val ID: ArrayList<String>, val originStationCode: ArrayList<String>,
    val stationPath: ArrayList<String>,
    val destinationStationCode: ArrayList<String>,
    val date: ArrayList<String>,
    val mapUrl: ArrayList<String>,
    val state: ArrayList<String>,
    val city: ArrayList<String>
): RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_data,parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindValue(ID,originStationCode,stationPath,destinationStationCode, date, mapUrl, state, city)
        val id = ID.get(position)

        holder.IDtv.text = id
//        holder.stationPathTv.text = stationPath.get(position)

        holder.stationPathTv.text = stationPath.get(position)
            //holder.mapImageView.setImageResource(mapUrl)
            holder.originStation.text = originStationCode.get(position)
            //holder.destiCode.text = destinationStationCode.get(position)
            holder.date1.text = date.get(position)
            //holder.distance1.text = .get(position)

        Glide.with(holder.itemView.context).load(mapUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mapImageView)




    }

    override fun getItemCount(): Int {
        return ID.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val IDtv = itemView.findViewById<TextView>(R.id.ID)
        val stationPathTv = itemView.findViewById<TextView>(R.id.stationPath)
        val mapImageView = itemView.findViewById<ImageView>(R.id.imgview)
        val originStation = itemView.findViewById<TextView>(R.id.originstation)
        //val destiCode = itemView.findViewById<TextView>(R.id.stationPath)
        val date1 = itemView.findViewById<TextView>(R.id.date)
        val distance1 = itemView.findViewById<TextView>(R.id.stationPath)

    }
}



