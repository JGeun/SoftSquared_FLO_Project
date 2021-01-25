package jgeun.study.flo_a_jeffrey.src.main.browse

import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartResponse
import retrofit2.Call
import retrofit2.http.GET

interface BrowseRetrofitInterface {
    @GET("/charts")
    fun getBrowseChart() : Call<BrowseChartResponse>
}