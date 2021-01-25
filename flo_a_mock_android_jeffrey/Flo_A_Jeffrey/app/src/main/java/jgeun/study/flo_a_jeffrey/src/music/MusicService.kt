package jgeun.study.flo_a_jeffrey.src.music

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicService(val view: MusicPlayerActivity){
    fun tryGetMusicInfo(songIdx: Int){
        val musicRetrofitInterface = ApplicationClass.sRetrofit.create(MusicRetrofitInterface::class.java)
        musicRetrofitInterface.getSongInfo(songIdx).enqueue(object : Callback<MusicResponse>{
            override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>) {
                view.onGetMusicInfoSuccess(response.body() as MusicResponse)
            }

            override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                view.onGetMusicInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}