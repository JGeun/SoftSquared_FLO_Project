package jgeun.study.flo_a_jeffrey.src.main.browse

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentBrowseBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.browse.flochartviewpager.BrowseFloChartAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartInfo
import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartResponse
import jgeun.study.flo_a_jeffrey.src.main.browse.popchartviewpager.BrowsePopChartAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseMovieAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseMovieItem
import jgeun.study.flo_a_jeffrey.src.main.browse.risechartviewpager.BrowseRiseChartAdapter

class BrowseFragment :
        BaseFragment<FragmentBrowseBinding>(FragmentBrowseBinding::bind, R.layout.fragment_browse), BrowseView {
    lateinit var mContext: Context
    private lateinit var btnChart: Button
    private lateinit var btnMovie: Button
    private lateinit var btnGenre: Button
    private lateinit var btnSituation: Button
    private lateinit var btnMood: Button

    private lateinit var btnDrawable: Drawable
    private lateinit var btnDrawableClick: Drawable
    private lateinit var btnDrawableMood: Drawable
    private lateinit var btnDrawableMoodClick: Drawable

    private lateinit var browseFloChartAdapter: BrowseFloChartAdapter
    private lateinit var floChartViewPager: ViewPager2

    private lateinit var browseRiseChartAdapter: BrowseRiseChartAdapter
    private lateinit var riseChartViewPager: ViewPager2

    private lateinit var browsePopChartAdapter: BrowsePopChartAdapter
    private lateinit var popChartViewPager: ViewPager2

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieViewAdapter: RecyclerView.Adapter<*>
    private lateinit var movieViewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (context as MainActivity).window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        showLoadingDialog(context!!)
        BrowseService(this).tryGetBrowseChart()
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).window.statusBarColor = ResourcesCompat.getColor(resources, R.color.browseBackground, null)
        btnDrawable = resources.getDrawable(R.drawable.browse_btn_tag_before, null)
        btnDrawableClick = resources.getDrawable(R.drawable.browse_btn_tag_after, null)
        btnDrawableMood = resources.getDrawable(R.drawable.browse_btn_tag_mood_before, null)
        btnDrawableMoodClick = resources.getDrawable(R.drawable.browse_btn_tag_mood_after, null)

        btnChart = binding.browseBtnChart
        btnMovie = binding.browseBtnMovie
        btnGenre = binding.browseBtnGenre
        btnSituation = binding.browseBtnSituation
        btnMood = binding.browseBtnMood

        setBtnClickListener()
        setMovieRecyclerView()
    }

    fun setBtnClickListener() {
        btnChart.setOnClickListener {
            btnChart.background = btnDrawableClick
            btnMovie.background = btnDrawable
            btnGenre.background = btnDrawable
            btnSituation.background = btnDrawable
            btnMood.background = btnDrawableMood
        }

        btnMovie.setOnClickListener {
            btnChart.background = btnDrawable
            btnMovie.background = btnDrawableClick
            btnGenre.background = btnDrawable
            btnSituation.background = btnDrawable
            btnMood.background = btnDrawableMood
        }

        btnGenre.setOnClickListener {
            btnChart.background = btnDrawable
            btnMovie.background = btnDrawable
            btnGenre.background = btnDrawableClick
            btnSituation.background = btnDrawable
            btnMood.background = btnDrawableMood
        }

        btnSituation.setOnClickListener {
            btnChart.background = btnDrawable
            btnMovie.background = btnDrawable
            btnGenre.background = btnDrawable
            btnSituation.background = btnDrawableClick
            btnMood.background = btnDrawableMood
        }

        btnMood.setOnClickListener {
            btnChart.background = btnDrawable
            btnMovie.background = btnDrawable
            btnGenre.background = btnDrawable
            btnSituation.background = btnDrawable
            btnMood.background = btnDrawableMoodClick
        }
    }

    fun setMovieRecyclerView(){
        val movieList = ArrayList<BrowseMovieItem>()
        movieList.add(BrowseMovieItem("[MV] 겨울잠", "장덕철"))
        movieList.add(BrowseMovieItem("[MV] 동거 (in the bed) (Crush)", "선우정아"))
        movieList.add(BrowseMovieItem("[티저] 위로가 필요한 나에게", "투앤비 (2NB) & by me"))
        movieList.add(BrowseMovieItem("[MV] Bloom", "BOYHOOD (남동현)"))
        movieList.add(BrowseMovieItem("[MV] 비밀리에 DAY6 (Even of Day) - '사랑, 이게 맞나 봐' 1월", "DAY6 (Even of Day)"))
        movieList.add(BrowseMovieItem("[티저] 5th Mini Album [YES.]:Concept Trailer #김지범", "골드차일드"))
        movieList.add(BrowseMovieItem("[MV] 우당탕 (Crush)", "MCND"))
        movieList.add(BrowseMovieItem("[MV]대저택", "BOYHOOD (남동현)"))
        movieList.add(BrowseMovieItem("[티저] 화 (火花) (HWAA) (Teaser1)", "여자)아이들"))
        movieList.add(BrowseMovieItem("[MV] In the Dark", "정세운"))

        movieViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        movieViewAdapter = BrowseMovieAdapter(context!!, movieList)

        movieRecyclerView = view!!.findViewById<RecyclerView>(R.id.browse_rv_movie).apply{
            setHasFixedSize(true)
            layoutManager = movieViewManager
            adapter = movieViewAdapter
        }
    }
    fun setFloChartViewPager(floChartList: ArrayList<BrowseChartItem>) {
        browseFloChartAdapter = BrowseFloChartAdapter(this, floChartList)
        floChartViewPager = binding.browseVpFloChart
        floChartViewPager.adapter = browseFloChartAdapter
        TabLayoutMediator(binding.browseTbFloChart, floChartViewPager) { tab, position -> }.attach()
    }

    fun setRiseChartViewPager(riseChartList: ArrayList<BrowseChartItem>) {
        browseRiseChartAdapter = BrowseRiseChartAdapter(this, riseChartList)
        riseChartViewPager = binding.browseVpRiseChart
        riseChartViewPager.adapter = browseRiseChartAdapter
        TabLayoutMediator(binding.browseTbRiseChart, riseChartViewPager) { tab, position -> }.attach()
    }

    fun setPopChartViewPager(popChartList: ArrayList<BrowseChartItem>) {
        browsePopChartAdapter = BrowsePopChartAdapter(this, popChartList)
        popChartViewPager = binding.browseVpPopChart
        popChartViewPager.adapter = browsePopChartAdapter
        TabLayoutMediator(binding.browseTbPopChart, popChartViewPager) { tab, position -> }.attach()
    }

    override fun onGetChartSuccess(response: BrowseChartResponse) {
        dismissLoadingDialog()

        if (response.code == 1000) {
            val chartArrayList = response.result
            val floChartArrayList = chartArrayList.get(0).songInfo
            var floChartList = ArrayList<BrowseChartItem>()
            for (chartList in floChartArrayList) {
                floChartList.add(BrowseChartItem(chartList.songIdx, chartList.songTitle, chartList.songImageUrl, chartList.songArtist))
            }
            val riseChartArrayList = chartArrayList.get(1).songInfo
            val riseChartList = ArrayList<BrowseChartItem>()
            for (chartList in riseChartArrayList) {
                riseChartList.add(BrowseChartItem(chartList.songIdx, chartList.songTitle, chartList.songImageUrl, chartList.songArtist))
            }
            val popChartArrayList = chartArrayList.get(2).songInfo
            val popChartList = ArrayList<BrowseChartItem>()
            for (chartList in popChartArrayList) {
                popChartList.add(BrowseChartItem(chartList.songIdx, chartList.songTitle, chartList.songImageUrl, chartList.songArtist))
            }

            setFloChartViewPager(floChartList)
            setRiseChartViewPager(riseChartList)
            setPopChartViewPager(popChartList)

        } else {
            showCustomToast("유효하지 않은 토큰입니다")
        }
    }

    override fun onGetChartFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}