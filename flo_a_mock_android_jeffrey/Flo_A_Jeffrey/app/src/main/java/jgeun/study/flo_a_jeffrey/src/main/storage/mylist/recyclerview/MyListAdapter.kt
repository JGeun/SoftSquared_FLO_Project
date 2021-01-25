package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.ResultUserList

class MyListAdapter(val context: Context, val userList : ArrayList<ResultUserList>) : RecyclerView.Adapter<MyListAdapter.MyListHolder>(){
    class MyListHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val totalLayout : RelativeLayout = itemView.findViewById(R.id.myList_item_totalLayout)
        val listAlbum : ImageView = itemView.findViewById(R.id.myList_item_albumImage)
        val listTitle : TextView = itemView.findViewById(R.id.myList_tv_listTitle)
        val songCount : TextView = itemView.findViewById(R.id.myList_tv_songCount)
        val playButton : ImageView = itemView.findViewById(R.id.myList_item_iv_play)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_my_list, parent, false)
        return MyListHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyListHolder, position: Int) {
        holder.totalLayout.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("listIdx", userList[position].listIdx)
            context.startActivity(intent)
        }
        
        val listImageUrl = userList[position].listImageUrl
        if(listImageUrl != null) //이게 없으면 null에러가 뜸
            Glide.with(context).load(listImageUrl).into(holder.listAlbum)
        holder.listTitle.setText(userList[position].listName)
        val songCount = userList[position].songCount
        holder.songCount.setText(songCount.toString())
        if(songCount != 0){
            holder.playButton.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_music_play, null))
        }
    }

    override fun getItemCount(): Int = userList.size


}