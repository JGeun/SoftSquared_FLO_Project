package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner

import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models.InnerListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InnerListRetrofitInterface {
    @GET("/lists/{listid}")
    fun onGetInnerList(@Path("listid") listid: Int) : Call<InnerListResponse>

}