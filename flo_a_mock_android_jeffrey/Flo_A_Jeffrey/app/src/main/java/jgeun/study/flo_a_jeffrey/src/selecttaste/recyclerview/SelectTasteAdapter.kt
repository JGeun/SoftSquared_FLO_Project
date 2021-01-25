package jgeun.study.flo_a_jeffrey.src.selecttaste.recyclerview

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.src.selecttaste.UserTasteStore
import java.net.URL

class SelectTasteAdapter(val context: Context, val selectTasteItem: ArrayList<SelectTasteItem>, val userSelectTasteStoreArray: ArrayList<UserTasteStore>)
    : RecyclerView.Adapter<SelectTasteAdapter.SelectTasteItemHolder>(){
    class SelectTasteItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleImage: FrameLayout = itemView.findViewById(R.id.selectTaste_item_frameLayout)
        val albumImage: ImageView = itemView.findViewById(R.id.selectTaste_rv_item_image)
        val checkImage: ImageView = itemView.findViewById(R.id.selectTaste_iv_checkImage)
        val checkHeart: ImageView = itemView. findViewById(R.id.selectTaste_iv_heart)
        val singer: TextView = itemView.findViewById(R.id.selectTaste_rv_item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTasteItemHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.select_taste_item, parent, false)
        return SelectTasteItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: SelectTasteItemHolder, position: Int) {
        if(selectTasteItem[position].artistTaste.equals("Y")){
            holder.checkImage.visibility = View.VISIBLE
            holder.checkHeart.visibility = View.VISIBLE
            holder.titleImage.setTag(2)
        }else{
            holder.checkImage.visibility = View.INVISIBLE
            holder.checkHeart.visibility = View.INVISIBLE
            holder.titleImage.setTag(1)
        }

        holder.titleImage.setOnClickListener{

            if(holder.titleImage.getTag() == 1){
                holder.checkImage.visibility = View.VISIBLE
                holder.checkHeart.visibility = View.VISIBLE

                for(i in 0 until userSelectTasteStoreArray.size){
                    val userTasteStore = userSelectTasteStoreArray.get(i)
                    if(userTasteStore.artistIdx == selectTasteItem[position].artistIdx){
                        userSelectTasteStoreArray.removeAt(i)
                        break
                    }
                }
                if(selectTasteItem[position].artistTaste == "N")
                    userSelectTasteStoreArray.add(UserTasteStore(selectTasteItem[position].artistIdx, "Y"))
                holder.titleImage.setTag(2)
            }else{
                holder.checkImage.visibility = View.INVISIBLE
                holder.checkHeart.visibility = View.INVISIBLE

                for(i in 0 until userSelectTasteStoreArray.size){
                    val userTasteStore = userSelectTasteStoreArray.get(i)
                    if(userTasteStore.artistIdx == selectTasteItem[position].artistIdx){
                        userSelectTasteStoreArray.removeAt(i)
                        break
                    }
                }
                if(selectTasteItem[position].artistTaste == "Y")
                    userSelectTasteStoreArray.add(UserTasteStore(selectTasteItem[position].artistIdx, "N"))
                holder.titleImage.setTag(1)
            }
        }

        val url = URL(selectTasteItem[position].artistProfileImageUrl)
        Log.d("url", url.toString())
        Glide.with(context).load(url).into(holder.albumImage)
        holder.albumImage.background = ShapeDrawable(OvalShape())
        holder.albumImage.clipToOutline = true

        holder.singer.setText(selectTasteItem[position].singer)
    }

    override fun getItemCount(): Int = selectTasteItem.size


}