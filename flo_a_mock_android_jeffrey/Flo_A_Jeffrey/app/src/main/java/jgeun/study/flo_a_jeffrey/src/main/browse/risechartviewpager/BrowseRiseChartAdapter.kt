package jgeun.study.flo_a_jeffrey.src.main.browse.risechartviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.BrowseFragment
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem

class BrowseRiseChartAdapter : FragmentStateAdapter{
    private  var riseChartList : ArrayList<BrowseChartItem>
    constructor(fragment: Fragment, riseChartList: ArrayList<BrowseChartItem>) : super(fragment) {
        this.riseChartList = riseChartList
    }
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> BrowseRiseChartFragment(1, riseChartList.subList(0, 5))
            1 -> BrowseRiseChartFragment(6, riseChartList.subList(5, 10))
            2 -> BrowseRiseChartFragment(11, riseChartList.subList(10, 15))
            3 -> BrowseRiseChartFragment(16, riseChartList.subList(15, 20))
            else -> BrowseRiseChartFragment(1, riseChartList)
        }
    }

}