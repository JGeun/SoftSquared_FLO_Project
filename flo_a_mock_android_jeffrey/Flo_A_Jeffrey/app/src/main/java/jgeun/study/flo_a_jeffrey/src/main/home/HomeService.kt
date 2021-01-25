package jgeun.study.flo_a_jeffrey.src.main.home

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) {

    fun tryGetHomeInfo(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomeInfo().enqueue(object : Callback<HomeInfoResponse> {
            override fun onResponse(call: Call<HomeInfoResponse>, response: Response<HomeInfoResponse>){
                view.onGetHomeInfoSuccess(response.body() as HomeInfoResponse)
            }

            override fun onFailure(call: Call<HomeInfoResponse>, t: Throwable) {
                view.onGetHomeInfoFailure(t.message ?: "통신 오류")
            }
        })
    }
}