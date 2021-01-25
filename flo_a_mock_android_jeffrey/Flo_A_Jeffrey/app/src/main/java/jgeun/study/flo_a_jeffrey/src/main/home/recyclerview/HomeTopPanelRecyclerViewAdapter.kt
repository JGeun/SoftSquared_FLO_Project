package jgeun.study.flo_a_jeffrey.src.main.home.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelrecyclerview.HomeTopPanelMusicItem

class HomeTopPanelRecyclerViewAdapter(val context: Context, val musicList: ArrayList<HomeTopPanelMusicItem>)
    : RecyclerView.Adapter<HomeTopPanelRecyclerViewAdapter.HomeTopPanelHolder>(){

    class HomeTopPanelHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val albumImage:ImageView = itemView.findViewById(R.id.home_toppanel_item_image)
        val songTitle: TextView = itemView.findViewById(R.id.home_toppanel_item_title)
        val artistName: TextView = itemView.findViewById(R.id.home_toppanel_item_artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTopPanelHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_toppanel_item, parent, false)
        return HomeTopPanelHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeTopPanelHolder, position: Int) {
        Glide.with(context).load(musicList[position].songImageUrl).into(holder.albumImage)
        holder.songTitle.setText(musicList[position].songTitle)
        holder.artistName.setText(musicList[position].artistName)
    }

    override fun getItemCount(): Int = musicList.size
}