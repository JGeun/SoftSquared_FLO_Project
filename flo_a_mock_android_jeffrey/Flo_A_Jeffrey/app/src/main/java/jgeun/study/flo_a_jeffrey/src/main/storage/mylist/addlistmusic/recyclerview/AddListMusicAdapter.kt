package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.addlistmusic.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.addlistmusic.AddListMusicActivity

class AddListMusicAdapter(val context: Context, val selectSongPositionArray: ArrayList<Int>) : RecyclerView.Adapter<AddListMusicAdapter.MusicAddListHolder>(){
    class MusicAddListHolder(itemView : View)  :RecyclerView.ViewHolder(itemView) {
        val totalLayout : ConstraintLayout = itemView.findViewById(R.id.addMusicList_totalLayout)
        val musicAlbum : ImageView = itemView.findViewById(R.id.addMusicList_item_album)
        val musicTitle : TextView = itemView.findViewById(R.id.addMusicList_item_musicTitle)
        val musicSinger : TextView = itemView.findViewById(R.id.addMusicList_item_musicSinger)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAddListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_add_music_list, parent, false)
        return MusicAddListHolder(itemView)
    }

    override fun onBindViewHolder(holder: MusicAddListHolder, position: Int) {
        val musicCount = (context as AddListMusicActivity).findViewById<TextView>(R.id.addMusicList_tv_selectCount)

        holder.totalLayout.setOnClickListener{
            if(!selectSongPositionArray.contains(position)){
                holder.totalLayout.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.addMusicListSelected, null))
                selectSongPositionArray.add(position)
                musicCount.setText(selectSongPositionArray.size.toString())
            }else{
                val index = selectSongPositionArray.indexOf(position)
                selectSongPositionArray.removeAt(index)
                holder.totalLayout.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.white, null))
                musicCount.setText(selectSongPositionArray.size.toString())
            }

            this.notifyDataSetChanged()
        }

        val button = context.findViewById<Button>(R.id.addMusicList_btn_add)
        if(selectSongPositionArray.size > 0){
            button.setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, null))
            musicCount.visibility = View.VISIBLE
            button.isEnabled = true

        }else{
            button.setTextColor(ResourcesCompat.getColor(context.resources, R.color.floForBtnDisabledTextColor, null))
            button.isEnabled = false
            musicCount.visibility = View.INVISIBLE
        }
        Glide.with(context).load(ApplicationClass.userMusicPlayList[position].songImageUrl).into(holder.musicAlbum)
        holder.musicTitle.setText(ApplicationClass.userMusicPlayList[position].songTitle)
        holder.musicSinger.setText(ApplicationClass.userMusicPlayList[position].artistName)
    }

    override fun getItemCount(): Int = ApplicationClass.userMusicPlayList.size
}