package jgeun.study.flo_a_jeffrey.src.main.home

import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeRetrofitInterface {
    @GET("/homes")
    fun getHomeInfo() : Call<HomeInfoResponse>
}