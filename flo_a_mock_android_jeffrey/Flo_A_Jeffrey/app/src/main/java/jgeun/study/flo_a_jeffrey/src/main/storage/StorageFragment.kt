package jgeun.study.flo_a_jeffrey.src.main.storage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentStorageBinding
import jgeun.study.flo_a_jeffrey.src.login.LoginActivity
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.StorageListView
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.MakeFolderResponse
import jgeun.study.flo_a_jeffrey.src.main.storage.viewPager2.StorageViewPagerAdapter

class StorageFragment : BaseFragment<FragmentStorageBinding>(FragmentStorageBinding::bind, R.layout.fragment_storage) {
    private lateinit var storageViewPagerAdapter: StorageViewPagerAdapter
    private lateinit var storageViewPager: ViewPager2

    val tabLayoutTextArray = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).window.statusBarColor = Color.WHITE
        (context as MainActivity).window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        binding.storageTvLoginBefore.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }

        val jwt = ApplicationClass.sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        if(jwt == null){
            tabLayoutTextArray.add("저장한 곡")
            tabLayoutTextArray.add("음악파일")

            binding.storeRlLoginAfter.visibility = View.INVISIBLE
            binding.storageTvLoginBefore.visibility = View.VISIBLE
            //binding.storageTl.layoutParams = LinearLayout.LayoutParams(600, ViewGroup.LayoutParams.WRAP_CONTENT)
        }else{
            tabLayoutTextArray.add("내 리스트")
            tabLayoutTextArray.add("♡좋아요")
            tabLayoutTextArray.add("저장한 곡")
            tabLayoutTextArray.add("많이 들은")
            tabLayoutTextArray.add("최근 감상")
            tabLayoutTextArray.add("음악파일")
            //binding.storageTl.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            binding.storeRlLoginAfter.visibility = View.VISIBLE
            binding.storageTvLoginBefore.visibility = View.INVISIBLE
        }

        storageViewPagerAdapter = StorageViewPagerAdapter(this)
        storageViewPager = binding.storageVp2
        storageViewPager.adapter = storageViewPagerAdapter
        TabLayoutMediator(binding.storageTl, storageViewPager){tab, position->
            tab.text = tabLayoutTextArray.get(position)
        }.attach()

    }
}