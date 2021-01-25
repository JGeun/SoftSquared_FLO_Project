package jgeun.study.flo_a_jeffrey.src.main.search.recyclerview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R

class SearchMusicFragmentAdapter(val context: Context, val musicList : ArrayList<SearchMusicItem>)
    : RecyclerView.Adapter<SearchMusicFragmentAdapter.SearchMusicHolder>(){

    class SearchMusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val index: TextView = itemView.findViewById<TextView>(R.id.search_rv_item_index)
        val musicTitle: TextView = itemView.findViewById<TextView>(R.id.search_rv_item_musicTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMusicHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_search_music_item, parent, false)
        return SearchMusicHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchMusicHolder, position: Int) {
        holder.itemView.isClickable = true
        holder.index.setText(musicList[position].index.toString())
        holder.musicTitle.setText(musicList[position].musicTitle)
    }

    override fun getItemCount(): Int = musicList.size

}