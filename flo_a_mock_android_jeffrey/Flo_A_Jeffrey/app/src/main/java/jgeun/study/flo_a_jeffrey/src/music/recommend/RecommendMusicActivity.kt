package jgeun.study.flo_a_jeffrey.src.music.recommend

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityMusicRecommendBinding
import jgeun.study.flo_a_jeffrey.src.music.recommend.recyclerview.RecommendMusicAdapter
import jgeun.study.flo_a_jeffrey.src.music.recommend.recyclerview.RecommendMusicItem
import jgeun.study.flo_a_jeffrey.src.music.recommend.recyclerview.RecommendRecyclerViewDecoration

class RecommendMusicActivity : BaseActivity<ActivityMusicRecommendBinding>(ActivityMusicRecommendBinding::inflate) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recommendList = ArrayList<RecommendMusicItem>()
        recommendList.add(RecommendMusicItem("뻔한남자", "이승기"))
        recommendList.add(RecommendMusicItem("잠이 오질 않네요", "장범준"))
        recommendList.add(RecommendMusicItem("사실 나는 (남자 Ver.)", "진건호"))
        recommendList.add(RecommendMusicItem("니가 없다면", "김나영"))
        recommendList.add(RecommendMusicItem("나랑 같이 걸을래", "적재"))
        recommendList.add(RecommendMusicItem("어차피 헤어진 사이", "이우 & 하이디"))
        recommendList.add(RecommendMusicItem("알았다면", "길구봉구"))
        recommendList.add(RecommendMusicItem("사실 나는", "경서예지"))
        recommendList.add(RecommendMusicItem("나의 밤", "에이치코드 (H:Code)"))
        recommendList.add(RecommendMusicItem("혼술하고 싶은 밤", "벤벤"))
       recommendList.add(RecommendMusicItem("오늘부터 1일", "정유진"))

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = RecommendMusicAdapter(this, recommendList)
        recyclerView = findViewById<RecyclerView>(R.id.recommend_rv_album).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(RecommendRecyclerViewDecoration(-30))

        binding.recommendMusicIvExit.setOnClickListener{
            finish()
        }
    }
}