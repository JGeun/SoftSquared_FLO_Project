package jgeun.study.flo_a_jeffrey.src.main.storage.viewPager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.storage.StorageFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.StorageMyListFragment

class StorageViewPagerAdapter(sf: StorageFragment) : FragmentStateAdapter(sf) {
    private val jwt = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)

    override fun createFragment(position: Int): Fragment {
        if(jwt == null){
            return when (position) {
                0 -> StorageStoreFragment()
                1 -> StorageStoreFragment()
                else -> StorageStoreFragment()
            }
        }else{
            return when (position) {
                0 -> StorageMyListFragment()
                1 -> StorageStoreFragment()
                2 -> StorageStoreFragment()
                3 -> StorageStoreFragment()
                4 -> StorageStoreFragment()
                5 -> StorageStoreFragment()
                else -> StorageStoreFragment()
            }
        }



    }

    override fun getItemCount(): Int {
        if(jwt == null){
            return 2
        }else
           return 6
    }
}