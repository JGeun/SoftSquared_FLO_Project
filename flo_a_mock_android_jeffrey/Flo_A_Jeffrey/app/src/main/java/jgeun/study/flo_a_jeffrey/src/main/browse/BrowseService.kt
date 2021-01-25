package jgeun.study.flo_a_jeffrey.src.main.browse

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.browse.models.BrowseChartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowseService(val view: BrowseFragment) {
    fun tryGetBrowseChart() {
        val browseRetrofitInterface = ApplicationClass.sRetrofit.create(BrowseRetrofitInterface::class.java)
        browseRetrofitInterface.getBrowseChart().enqueue(object : Callback<BrowseChartResponse> {

            override fun onResponse(call: Call<BrowseChartResponse>, response: Response<BrowseChartResponse>) {
                view.onGetChartSuccess(response.body() as BrowseChartResponse)
            }

            override fun onFailure(call: Call<BrowseChartResponse>, t: Throwable) {
                view.onGetChartFailure(t.message ?: "통신 오류")
            }
        })
    }
}