package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models.ResultSong

class InnerListAdapter(val context: Context, val songArray: ArrayList<ResultSong>) : RecyclerView.Adapter<InnerListAdapter.InnerListHolder>(){
    class InnerListHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val musicAlbum : ImageView = itemView.findViewById(R.id.innerList_item_albumImage)
        val musicTitle : TextView = itemView.findViewById(R.id.innerList_item_musicTitle)
        val artistName : TextView = itemView.findViewById(R.id.innerList_item_artistName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_inner_list, parent, false)
        return InnerListHolder(itemView)
    }

    override fun onBindViewHolder(holder: InnerListHolder, position: Int) {
        Glide.with(context).load(songArray[position].songImageUrl).into(holder.musicAlbum)
        holder.musicTitle.setText(songArray[position].songTitle)
        holder.artistName.setText(songArray[position].artistName)
    }

    override fun getItemCount(): Int = songArray.size

}