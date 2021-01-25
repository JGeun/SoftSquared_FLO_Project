package jgeun.study.flo_a_jeffrey.src.music.list.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.foreground.CreateNotification
import jgeun.study.flo_a_jeffrey.src.music.list.MusicListActivity
import jgeun.study.flo_a_jeffrey.src.music.setting.MusicSettingActivity

class MusicListAdapter(val context: Context)
    : RecyclerView.Adapter<MusicListAdapter.MusicListItemHolder>(){

    class MusicListItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val musicRelativeLayout : RelativeLayout = itemView.findViewById(R.id.musicList_item_rl)
        val albumImage : ImageView = itemView.findViewById(R.id.musicList_item_albumImage)
        val songTitle : TextView = itemView.findViewById(R.id.musicList_item_songTitle)
        val artistName : TextView = itemView.findViewById(R.id.musicList_item_artistName)
        val musicSetting : ImageView = itemView.findViewById(R.id.musicList_item_setting)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.music_list_item, parent, false)
        return MusicListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: MusicListItemHolder, position: Int) {
        holder.musicRelativeLayout.setOnClickListener{
            ApplicationClass.musicPlayIndex = position
            val album = (context as MusicListActivity).findViewById<ImageView>(R.id.musicList_iv_album)
            Glide.with(context).load(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songImageUrl).into(album)
            this.notifyDataSetChanged()
            CreateNotification.createNotification(context, ApplicationClass.userMusicPlayList.get(ApplicationClass.musicPlayIndex),
                    R.drawable.ic_notification_play, ApplicationClass.musicPlayIndex, ApplicationClass.userMusicPlayList.size - 1)
        }

        Glide.with(context).load(ApplicationClass.userMusicPlayList[position].songImageUrl).into(holder.albumImage)
        if(position == ApplicationClass.musicPlayIndex){
            holder.songTitle.setTextColor(ResourcesCompat.getColor(context.resources, R.color.floForBtnColor, null))
            holder.artistName.setTextColor(ResourcesCompat.getColor(context.resources, R.color.floForBtnColor, null))
        }else{
            holder.songTitle.setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, null))
            holder.artistName.setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, null))
        }
        holder.songTitle.setText(ApplicationClass.userMusicPlayList[position].songTitle)
        holder.artistName.setText(ApplicationClass.userMusicPlayList[position].artistName)
        holder.musicSetting.setOnClickListener{
             val settingIntent = Intent(context, MusicSettingActivity::class.java)
            settingIntent.putExtra("position", position)
            settingIntent.putExtra("songIdx", ApplicationClass.userMusicPlayList[position].songIdx)
            (context as MusicListActivity).startActivityForResult(settingIntent, 1000)
        }

    }

    override fun getItemCount(): Int = ApplicationClass.userMusicPlayList.size

}