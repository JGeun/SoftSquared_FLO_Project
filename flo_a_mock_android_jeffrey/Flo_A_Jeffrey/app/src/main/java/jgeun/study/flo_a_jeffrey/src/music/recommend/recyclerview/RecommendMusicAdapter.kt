package jgeun.study.flo_a_jeffrey.src.music.recommend.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R

class RecommendMusicAdapter(val context: Context, val recommendMusicItem: ArrayList<RecommendMusicItem>)
    : RecyclerView.Adapter<RecommendMusicAdapter.RecommendMusicItemHolder>(){
    class RecommendMusicItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumTitle :TextView = itemView.findViewById(R.id.recommend_rv_item_title)
        val albumSinger :TextView = itemView.findViewById(R.id.recommend_rv_item_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendMusicItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recommend_music_item, parent, false)
        return RecommendMusicItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecommendMusicItemHolder, position: Int) {
        holder.albumTitle.setText(recommendMusicItem[position].title)
        holder.albumSinger.setText(recommendMusicItem[position].singer)
    }

    override fun getItemCount(): Int = recommendMusicItem.size

}