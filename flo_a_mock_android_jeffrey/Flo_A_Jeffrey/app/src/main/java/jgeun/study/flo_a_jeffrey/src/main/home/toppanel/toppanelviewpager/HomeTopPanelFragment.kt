package jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelviewpager

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentHomeToppanelBinding
import jgeun.study.flo_a_jeffrey.src.main.home.HomeFragmentView
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse
import jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelrecyclerview.HomeTopPanelMusicItem
import jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelrecyclerview.HomeTopPanelRecyclerViewAdapter
import jgeun.study.flo_a_jeffrey.src.setting.SettingFragment


class HomeTopPanelFragment(val listName: String, val listTimeStamp: String, val songCount: Int, val songInfo : ArrayList<HomeTopPanelMusicItem>)
    : BaseFragment<FragmentHomeToppanelBinding>(FragmentHomeToppanelBinding::bind, R.layout.fragment_home_toppanel){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeTvTopPanelTitle.setText(listName)
        binding.homeTopPanelTvSongCount.setText(songCount.toString())

        viewManager = LinearLayoutManager(context)
        viewAdapter = HomeTopPanelRecyclerViewAdapter(context!!, songInfo)

        recyclerView = view!!.findViewById<RecyclerView>(R.id.home_topPanel_rv_song).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.isNestedScrollingEnabled = false


    }
}