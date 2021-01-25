package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.addlistmusic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityAddListMusicBinding
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.StorageMyListFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.addlistmusic.recyclerview.AddListMusicAdapter

class AddListMusicActivity : BaseActivity<ActivityAddListMusicBinding>(ActivityAddListMusicBinding::inflate){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var selectSongPositionArray = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(ApplicationClass.userMusicPlayList.size == 0){
            binding.addMusicListTvNoPlayList.visibility = View.VISIBLE
            binding.addMusicListLlSelectAll.visibility = View.INVISIBLE
        }else{
            binding.addMusicListTvNoPlayList.visibility = View.INVISIBLE
            binding.addMusicListLlSelectAll.visibility = View.VISIBLE

            viewManager = LinearLayoutManager(this)
            viewAdapter = AddListMusicAdapter(this, selectSongPositionArray)

            recyclerView = findViewById<RecyclerView>(R.id.addMusicList_rv).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

            binding.addMusicListBtnAdd.setOnClickListener{
                val intent = Intent(this, StorageMyListFragment::class.java)
                intent.putIntegerArrayListExtra("selectSongPositionArray", selectSongPositionArray)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}