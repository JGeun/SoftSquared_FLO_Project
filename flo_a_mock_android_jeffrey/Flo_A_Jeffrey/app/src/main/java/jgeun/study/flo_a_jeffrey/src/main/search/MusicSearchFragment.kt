package jgeun.study.flo_a_jeffrey.src.main.search

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amar.library.ui.StickyScrollView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentMusicSearchBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.search.recyclerview.SearchMusicFragmentAdapter
import jgeun.study.flo_a_jeffrey.src.main.search.recyclerview.SearchMusicItem

class MusicSearchFragment(context: Context) : BaseFragment<FragmentMusicSearchBinding>(FragmentMusicSearchBinding::bind, R.layout.fragment_music_search){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var stickyScrollView : StickyScrollView

    private var musicList =  ArrayList<SearchMusicItem>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).window.statusBarColor = Color.WHITE
        (context as MainActivity).window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        setMusicList()

        viewManager = LinearLayoutManager(context)
        viewAdapter = SearchMusicFragmentAdapter(context!!, musicList)

        recyclerView = view.findViewById<RecyclerView>(R.id.search_rv_musicChart).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.isNestedScrollingEnabled = false

        stickyScrollView = binding.searchStickySv
        stickyScrollView.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(stickyScrollView.canScrollVertically(-1)) //최하단일때
                    recyclerView.isNestedScrollingEnabled = true
                else
                    recyclerView.isNestedScrollingEnabled = false
                return false;
            }
        })

        recyclerView.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(stickyScrollView.canScrollVertically(-1)) //최하단일때
                    recyclerView.isNestedScrollingEnabled = true
                else
                    recyclerView.isNestedScrollingEnabled = false
                return false
            }
        })
    }

    fun setMusicList(){
        musicList.clear()
        musicList.add(SearchMusicItem(1, "우리의 밤은 당신의 낮보다 아름답다"))
        musicList.add(SearchMusicItem(2, "아이유"))
        musicList.add(SearchMusicItem(3, "싱어게인"))
        musicList.add(SearchMusicItem(4, "방탄소년단"))
        musicList.add(SearchMusicItem(5, "이문세"))
        musicList.add(SearchMusicItem(6, "블랙핑크"))
        musicList.add(SearchMusicItem(7, "여신강림 OST"))
        musicList.add(SearchMusicItem(8, "이수"))
        musicList.add(SearchMusicItem(9, "내 입술.. 따뜻한 커피처럼"))
        musicList.add(SearchMusicItem(10, "태연"))
        musicList.add(SearchMusicItem(11, "박효신"))
        musicList.add(SearchMusicItem(12, "트와이스"))
        musicList.add(SearchMusicItem(13, "잔나비"))
        musicList.add(SearchMusicItem(14, "밝게 빛나는 별이 되어 비춰줄게"))
        musicList.add(SearchMusicItem(15, "밤하늘의 별을"))
        musicList.add(SearchMusicItem(16, "엠씨더맥스"))
        musicList.add(SearchMusicItem(17, "쇼미더머니9"))
        musicList.add(SearchMusicItem(18, "미스트롯2"))
        musicList.add(SearchMusicItem(19, "자장가"))
        musicList.add(SearchMusicItem(20, "임영웅"))
    }
}