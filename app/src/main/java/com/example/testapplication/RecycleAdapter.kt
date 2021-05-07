package com.example.testapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecycleAdapter(var gamesList:ArrayList<Game>):RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_card,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return gamesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = gamesList[position]
        holder.gameNameText.text = "Название игры: " + currentItem.game?.name
        holder.countOfChannelsText.text = "Количество каналов: " + currentItem.channels.toString()
        holder.countOfViewersText.text = "Количество просмотров: " + currentItem.viewers.toString()
        Glide.with(holder.itemView).load(currentItem.game?.box?.large).into(holder.gameImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countOfViewersText: TextView = itemView.findViewById(R.id.countOfViewers)
        val countOfChannelsText: TextView = itemView.findViewById(R.id.countOfChannels)
        val gameNameText: TextView = itemView.findViewById(R.id.gameName)
        val gameImage: ImageView = itemView.findViewById(R.id.gameImage)
    }


}