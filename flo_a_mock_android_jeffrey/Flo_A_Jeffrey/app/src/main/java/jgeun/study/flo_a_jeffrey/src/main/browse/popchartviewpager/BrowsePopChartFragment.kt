package jgeun.study.flo_a_jeffrey.src.main.browse.popchartviewpager

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentBrowsePopChartBinding
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartAdapter
import jgeun.study.flo_a_jeffrey.src.main.browse.recyclerview.BrowseChartItem

class BrowsePopChartFragment(val startIdx:Int, val popChartList: MutableList<BrowseChartItem>) : BaseFragment<FragmentBrowsePopChartBinding>
(FragmentBrowsePopChartBinding::bind, R.layout.fragment_browse_pop_chart){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        viewAdapter = BrowseChartAdapter(context!!, startIdx, popChartList)

        recyclerView = view.findViewById<RecyclerView>(R.id.browse_rv_pop_chart).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.isNestedScrollingEnabled = false
    }
}