package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models.InnerListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InnerListService(val view : InnerListFragment){
    fun tryGetInnerList(listid: Int){
        val innerListRetrofitInterface = ApplicationClass.sRetrofit.create(InnerListRetrofitInterface::class.java)
        innerListRetrofitInterface.onGetInnerList(listid).enqueue(object: Callback<InnerListResponse>{
            override fun onResponse(call: Call<InnerListResponse>, response: Response<InnerListResponse>) {
                view.onGetInnerListSuccess(response.body() as InnerListResponse)
            }

            override fun onFailure(call: Call<InnerListResponse>, t: Throwable) {
                view.onGetInnerListFailure(t.message ?: "통신 오류")
            }

        })
    }
}