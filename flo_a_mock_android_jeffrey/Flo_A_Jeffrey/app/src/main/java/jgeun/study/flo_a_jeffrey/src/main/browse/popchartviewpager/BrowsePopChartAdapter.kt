package jgeun.study.flo_a_jeffrey.src.main.browse.popchartviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.BrowseFragment
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem

class BrowsePopChartAdapter: FragmentStateAdapter{
    private  var popChartList : ArrayList<BrowseChartItem>
    constructor(fragment: Fragment, popChartList: ArrayList<BrowseChartItem>) : super(fragment) {
        this.popChartList = popChartList
    }
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BrowsePopChartFragment(1, popChartList.subList(0,5))
            1 -> BrowsePopChartFragment(6, popChartList.subList(5,10))
            2 -> BrowsePopChartFragment(11, popChartList.subList(10,15))
            3 -> BrowsePopChartFragment(16, popChartList.subList(15,20))
            else -> BrowsePopChartFragment(1, popChartList)
        }
    }
}