package jgeun.study.flo_a_jeffrey.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentHomeBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse
import jgeun.study.flo_a_jeffrey.src.main.home.recyclerview.HomeAlbumItem
import jgeun.study.flo_a_jeffrey.src.main.home.recyclerview.HomeFragmentAdapter
import jgeun.study.flo_a_jeffrey.src.main.home.recyclerview.HomeTopPanelItem
import jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelrecyclerview.HomeTopPanelMusicItem
import jgeun.study.flo_a_jeffrey.src.main.home.toppanel.toppanelviewpager.HomeTopPanelViewPagerAdapter
import jgeun.study.flo_a_jeffrey.src.setting.SettingFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentView {
    private lateinit var homeTopPanelViewPager: ViewPager2
    private lateinit var homeTopPanelViewPagerAdapter: HomeTopPanelViewPagerAdapter

    private lateinit var todayRecyclerView: RecyclerView
    private lateinit var todayViewAdapter: RecyclerView.Adapter<*>
    private lateinit var todayViewManager: RecyclerView.LayoutManager

    private lateinit var originalRecyclerView: RecyclerView
    private lateinit var originalViewAdapter: RecyclerView.Adapter<*>
    private lateinit var originalViewManager: RecyclerView.LayoutManager

    private lateinit var castRecyclerView: RecyclerView
    private lateinit var castViewAdapter: RecyclerView.Adapter<*>
    private lateinit var castViewManager: RecyclerView.LayoutManager

    private lateinit var genreRecyclerView: RecyclerView
    private lateinit var genreViewAdapter: RecyclerView.Adapter<*>
    private lateinit var genreViewManager: RecyclerView.LayoutManager

    private lateinit var videoRecyclerView: RecyclerView
    private lateinit var videoViewAdapter: RecyclerView.Adapter<*>
    private lateinit var videoViewManager: RecyclerView.LayoutManager

    private lateinit var audioRecyclerView: RecyclerView
    private lateinit var audioViewAdapter: RecyclerView.Adapter<*>
    private lateinit var audioViewManager: RecyclerView.LayoutManager

    val jwt = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).window.apply {
            this.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }


        if (jwt != null) {
            //binding.homeToppanelSelectTasteAfter.visibility = View.VISIBLE
            binding.homeToppanelIvSelectAfter.visibility = View.VISIBLE
            binding.homeToppanelIvPlay.visibility = View.VISIBLE
            binding.homeVpTopPanel.visibility = View.INVISIBLE
        } else {
            //binding.homeToppanelSelectTasteAfter.visibility = View.INVISIBLE
            binding.homeToppanelIvSelectAfter.visibility = View.INVISIBLE
            binding.homeToppanelIvPlay.visibility = View.INVISIBLE
            binding.homeVpTopPanel.visibility = View.VISIBLE
        }
        binding.homeToppanelIvSetting.setOnClickListener {
            val homeFragmentManager = activity!!.supportFragmentManager
            homeFragmentManager.beginTransaction().replace(R.id.main_frm, SettingFragment()).commitAllowingStateLoss()
        }
        showLoadingDialog(context!!)
        HomeService(this).tryGetHomeInfo()

        /*
        homeTopPanelViewPager = binding.homeVpTopPanel
        homeTopPanelViewPagerAdapter = HomeTopPanelViewPagerAdapter(this)
        homeTopPanelViewPager.adapter = homeTopPanelViewPagerAdapter
        TabLayoutMediator(binding.homeTbTopPanel, homeTopPanelViewPager){tab, position -> }.attach()*/
    }

    override fun onGetHomeInfoSuccess(response: HomeInfoResponse) {
        dismissLoadingDialog()

        if (response.code != 2000) {
            val TopPanelListArray = ArrayList<HomeTopPanelItem>()
            if (jwt == null) {
                val mainResult = response.result.mainResult
                for (result in mainResult) {
                    val songInfo = result.songInfo
                    val musicArray = ArrayList<HomeTopPanelMusicItem>()
                    for (song in songInfo)
                        musicArray.add(HomeTopPanelMusicItem(song.songIdx, song.songTitle, song.artistName,
                                song.songImageUrl, song.artistImageUrl))

                    TopPanelListArray.add(HomeTopPanelItem(result.listName, result.listTimestamp, result.songCount, musicArray))
                }
                homeTopPanelViewPagerAdapter = HomeTopPanelViewPagerAdapter(this, TopPanelListArray)
                homeTopPanelViewPager = binding.homeVpTopPanel
                homeTopPanelViewPager.adapter = homeTopPanelViewPagerAdapter
                TabLayoutMediator(binding.browseTbFloChart, homeTopPanelViewPager) { tab, position -> }.attach()
            }

            val todayArrayList = response.result.todayReleasedMusic
            val todayList = ArrayList<HomeAlbumItem>()
            for (i in 0 until todayArrayList.size) {
                todayList.add(HomeAlbumItem(todayArrayList.get(i).albumImageUrl, todayArrayList.get(i).albumTitle, todayArrayList.get(i).artistName))
            }

            val recommendList = response.result.recommendedLists
            binding.homeTvRecommend1.setText(recommendList.get(0).recommendedListName)
            val listInfoArray = recommendList.get(0).listInfo
            val originalList = ArrayList<HomeAlbumItem>()
            for (listInfo in listInfoArray) {
                originalList.add(HomeAlbumItem(listInfo.listImageUrl, listInfo.listName, ""))
            }

            setTodayRecyclerView(todayList)
            setOriginalRecyclerView(originalList)
            setOtherRecyclerView(todayList)
        }

    }

    override fun onGetHomeInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    fun setTodayRecyclerView(todayList: ArrayList<HomeAlbumItem>) {
        todayViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        todayViewAdapter = HomeFragmentAdapter(context!!, todayList)
        todayRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_today).apply {
            setHasFixedSize(true)
            layoutManager = todayViewManager
            adapter = todayViewAdapter
        }
    }

    fun setOriginalRecyclerView(originalList: ArrayList<HomeAlbumItem>) {
        originalViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        originalViewAdapter = HomeFragmentAdapter(context!!, originalList)
        originalRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_original).apply {
            setHasFixedSize(true)
            layoutManager = originalViewManager
            adapter = originalViewAdapter
        }
    }

    fun setOtherRecyclerView(tempList: ArrayList<HomeAlbumItem>) {
        castViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        castViewAdapter = HomeFragmentAdapter(context!!, tempList)
        castRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_cast).apply {
            setHasFixedSize(true)
            layoutManager = castViewManager
            adapter = castViewAdapter
        }

        genreViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        genreViewAdapter = HomeFragmentAdapter(context!!, tempList)
        genreRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_genre).apply {
            setHasFixedSize(true)
            layoutManager = genreViewManager
            adapter = genreViewAdapter
        }

        videoViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        videoViewAdapter = HomeFragmentAdapter(context!!, tempList)
        videoRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_video).apply {
            setHasFixedSize(true)
            layoutManager = videoViewManager
            adapter = videoViewAdapter
        }

        audioViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        audioViewAdapter = HomeFragmentAdapter(context!!, tempList)
        audioRecyclerView = view!!.findViewById<RecyclerView>(R.id.home_rv_audio).apply {
            setHasFixedSize(true)
            layoutManager = audioViewManager
            adapter = audioViewAdapter
        }
    }
}