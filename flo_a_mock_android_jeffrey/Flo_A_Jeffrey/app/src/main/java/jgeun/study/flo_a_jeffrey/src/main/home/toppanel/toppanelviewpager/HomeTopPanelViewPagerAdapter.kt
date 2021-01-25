package jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem
import jgeun.study.flo_a_jeffrey.src.main.home.recyclerview.HomeTopPanelItem

class HomeTopPanelViewPagerAdapter : FragmentStateAdapter {
    private var homeTopPanelItem : ArrayList<HomeTopPanelItem>
    constructor(fragment: Fragment, homeTopPanelItem: ArrayList<HomeTopPanelItem>) : super(fragment) {
        this.homeTopPanelItem = homeTopPanelItem
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeTopPanelFragment(homeTopPanelItem.get(0).listName, homeTopPanelItem.get(0).listTimestamp, homeTopPanelItem.get(0).songCount, homeTopPanelItem.get(0).songInfo)
            1 -> HomeTopPanelFragment(homeTopPanelItem.get(1).listName, homeTopPanelItem.get(1).listTimestamp, homeTopPanelItem.get(1).songCount, homeTopPanelItem.get(1).songInfo)
            2 -> HomeTopPanelFragment(homeTopPanelItem.get(2).listName, homeTopPanelItem.get(2).listTimestamp, homeTopPanelItem.get(2).songCount, homeTopPanelItem.get(2).songInfo)
            3 -> HomeTopPanelFragment(homeTopPanelItem.get(3).listName, homeTopPanelItem.get(3).listTimestamp, homeTopPanelItem.get(3).songCount, homeTopPanelItem.get(3).songInfo)
            4 -> HomeTopPanelFragment(homeTopPanelItem.get(4).listName, homeTopPanelItem.get(4).listTimestamp, homeTopPanelItem.get(4).songCount, homeTopPanelItem.get(4).songInfo)
            else -> HomeTopPanelFragment(homeTopPanelItem.get(0).listName, homeTopPanelItem.get(0).listTimestamp, homeTopPanelItem.get(0).songCount, homeTopPanelItem.get(0).songInfo)
        }
    }
}