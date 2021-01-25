package jgeun.study.flo_a_jeffrey.src.main.browse.risechartviewpager

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentBrowseRiseChartBinding
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem

class BrowseRiseChartFragment(val startIdx: Int, val riseChartList: MutableList<BrowseChartItem>) : BaseFragment<FragmentBrowseRiseChartBinding>
(FragmentBrowseRiseChartBinding::bind, R.layout.fragment_browse_rise_chart){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        viewAdapter = BrowseChartAdapter(context!!, startIdx, riseChartList)

        recyclerView = view.findViewById<RecyclerView>(R.id.browse_rv_rise_chart).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.isNestedScrollingEnabled = false
    }
}