package jgeun.study.flo_a_jeffrey.src.main.home.recyclerview

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R

class HomeFragmentAdapter(val context: Context, val homeAlbumItem: ArrayList<HomeAlbumItem>)
    : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentItemHolder>() {
    class HomeFragmentItemHolder(itemView: View)  : RecyclerView.ViewHolder(itemView){
        val albumImage : ImageView = itemView.findViewById(R.id.home_rv_item_album)
        val albumPlay : ImageView = itemView.findViewById(R.id.home_rv_item_play)
        val albumTitle : TextView = itemView.findViewById(R.id.home_rv_item_title)
        val albumSinger : TextView = itemView.findViewById(R.id.home_rv_item_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_album_item, parent, false)
        return HomeFragmentItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeFragmentItemHolder, position: Int) {
        if(homeAlbumItem[position].url.length > 0)
            Glide.with(context).load(homeAlbumItem[position].url).into(holder.albumImage)

        var title = homeAlbumItem[position].title
//        if(title.length > 10)
//            title = title.substring(0, 10) + "\n" + title.substring(10, title.length)
        title.replace(" ", "\u00A0")
        holder.albumTitle.setText(title)
        holder.albumSinger.setText(homeAlbumItem[position].singer)
    }

    override fun getItemCount(): Int = homeAlbumItem.size

}