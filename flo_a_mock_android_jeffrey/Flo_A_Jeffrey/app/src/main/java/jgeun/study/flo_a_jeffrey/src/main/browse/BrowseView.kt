package jgeun.study.flo_a_jeffrey.src.main.browse

import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartResponse

interface BrowseView {
    fun onGetChartSuccess(response: BrowseChartResponse)
    fun onGetChartFailure(message: String)
}