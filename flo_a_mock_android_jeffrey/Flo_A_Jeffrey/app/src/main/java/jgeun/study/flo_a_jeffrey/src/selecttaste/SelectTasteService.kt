package jgeun.study.flo_a_jeffrey.src.selecttaste

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTaste
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTasteResponse
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.SelectTasteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTasteService(val view: SelectTasteActivity) {

    fun tryGetUserArtistTaste(){
        val selectTasteRetroInterface = ApplicationClass.sRetrofit.create(SelectTasteRetroInterface::class.java)
        selectTasteRetroInterface.getUserArtistTaste().enqueue(object: Callback<SelectTasteResponse>{
            override fun onResponse(call: Call<SelectTasteResponse>, response: Response<SelectTasteResponse>) {
                view.onGetArtistInfoSuccess(response.body() as SelectTasteResponse)
            }

            override fun onFailure(call: Call<SelectTasteResponse>, t: Throwable) {
                view.onGetArtistInfoFailure(t.message?: "통신 오류")
            }

        })
    }

    fun tryPutUserTaste(putUserTasteRequest : PutUserTaste){
        val selectTasteRetroInterface = ApplicationClass.sRetrofit.create(SelectTasteRetroInterface::class.java)
        selectTasteRetroInterface.putUserTaste(putUserTasteRequest).enqueue(object: Callback<PutUserTasteResponse>{
            override fun onResponse(call: Call<PutUserTasteResponse>, response: Response<PutUserTasteResponse>) {
                view.onPutUserTasteSuccess(response.body() as PutUserTasteResponse)
            }

            override fun onFailure(call: Call<PutUserTasteResponse>, t: Throwable) {
                view.onPutUserTasteFailure(t.message?: "통신 오류")
            }

        })
    }
}