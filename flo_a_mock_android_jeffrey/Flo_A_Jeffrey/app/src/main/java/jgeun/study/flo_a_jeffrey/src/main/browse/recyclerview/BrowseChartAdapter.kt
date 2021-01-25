package jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.UserPlayList
import jgeun.study.flo_a_jeffrey.src.main.MainActivity

class BrowseChartAdapter(val context: Context,val startIdx:Int, val floChartList: MutableList<BrowseChartItem>)
    : RecyclerView.Adapter<BrowseChartAdapter.BrowseFloChartHolder>() {

    class BrowseFloChartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val album: ImageView = itemView.findViewById(R.id.browse_chart_item_iv_album)
        val musicIndex : TextView = itemView.findViewById(R.id.browse_chart_item_tv_musicIndex)
        val musicTitle: TextView = itemView.findViewById(R.id.browse_chart_item_tv_musicTitle)
        val musicSinger: TextView = itemView.findViewById(R.id.browse_chart_item_tv_musicSinger)
        val musicPlay : ImageView =itemView.findViewById(R.id.browse_chart_item_iv_music_play)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseFloChartHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.browse_chart_fragment_item, parent, false)
        return BrowseFloChartHolder(itemView)
    }

    override fun onBindViewHolder(holder: BrowseFloChartHolder, position: Int) {
        Glide.with(context).load(floChartList[position].songImageUrl).into(holder.album)
        holder.musicIndex.setText((startIdx+position).toString())
        holder.musicTitle.setText(floChartList[position].songTitle)
        holder.musicSinger.setText(floChartList[position].songArtist)
        holder.musicPlay.setOnClickListener{
            ApplicationClass.userMusicPlayList.add(UserPlayList(floChartList[position].songIdx,
                    floChartList[position].songTitle, floChartList[position].songImageUrl, floChartList[position].songArtist))
            if(ApplicationClass.userMusicPlayList.size == 1){
                (context as MainActivity).findViewById<LinearLayout>(R.id.main_ll_musicPlayer_noMusic).visibility = View.INVISIBLE
                context.findViewById<LinearLayout>(R.id.main_ll_musicPlayer).visibility = View.VISIBLE
                context.findViewById<TextView>(R.id.main_tv_songTitle).setText(floChartList[position].songTitle)
                context.findViewById<TextView>(R.id.main_tv_artistName).setText(floChartList[position].songArtist)
            }
            Log.d("UserPlaySize", ApplicationClass.userMusicPlayList.size.toString())
        }
    }

    override fun getItemCount(): Int = floChartList.size
}