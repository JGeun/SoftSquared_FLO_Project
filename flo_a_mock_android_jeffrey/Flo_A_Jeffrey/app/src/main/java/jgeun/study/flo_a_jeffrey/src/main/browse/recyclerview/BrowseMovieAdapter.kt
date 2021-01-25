package jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R

class BrowseMovieAdapter(val context: Context, val movieList: ArrayList<BrowseMovieItem>)
    : RecyclerView.Adapter<BrowseMovieAdapter.BrowseMovieHolder>() {

    class BrowseMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicTitle: TextView = itemView.findViewById(R.id.browse_movie_item_title)
        val musicSinger: TextView = itemView.findViewById(R.id.browse_movie_item_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseMovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.browse_movie_item, parent, false)
        return BrowseMovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: BrowseMovieHolder, position: Int) {
        holder.musicTitle.setText(movieList[position].songTitle)
        holder.musicSinger.setText(movieList[position].songSinger)
    }

    override fun getItemCount(): Int  = movieList.size
}
