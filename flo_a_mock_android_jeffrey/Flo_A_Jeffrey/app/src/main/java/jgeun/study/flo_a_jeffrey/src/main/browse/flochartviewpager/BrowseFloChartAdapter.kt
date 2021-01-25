package jgeun.study.flo_a_jeffrey.src.main.browse.flochartviewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.BrowseFragment
import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartInfo
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem

class BrowseFloChartAdapter : FragmentStateAdapter{
    private lateinit var floChartList : ArrayList<BrowseChartItem>
    constructor(fragment: Fragment, floChartList: ArrayList<BrowseChartItem>) : super(fragment) {
        this.floChartList = floChartList
    }
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BrowseFloChartFragment(1, floChartList.subList(0,5))
            1 -> BrowseFloChartFragment(6, floChartList.subList(5,10))
            2 -> BrowseFloChartFragment(11, floChartList.subList(10,15))
            3 -> BrowseFloChartFragment(16, floChartList.subList(15,20))
            else -> BrowseFloChartFragment(1, floChartList)
        }
    }
}