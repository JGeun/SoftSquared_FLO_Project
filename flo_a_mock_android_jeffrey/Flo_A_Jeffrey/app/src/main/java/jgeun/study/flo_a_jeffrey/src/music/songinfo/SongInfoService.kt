package jgeun.study.flo_a_jeffrey.src.music.songinfo

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.music.songinfo.models.SongInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongInfoService(val view: SongInfoFragment){
    fun tryGetSongInfo(songIdx: Int){
        val songInfoRetrofitInterface = ApplicationClass.sRetrofit.create(SongInfoRetrofitInterface::class.java)
        songInfoRetrofitInterface.getSongInfo(songIdx).enqueue(object : Callback<SongInfoResponse>{
            override fun onResponse(call: Call<SongInfoResponse>, response: Response<SongInfoResponse>) {
               view.onGetSongInfoSuccess(response.body() as SongInfoResponse)
            }

            override fun onFailure(call: Call<SongInfoResponse>, t: Throwable) {
                view.onGetSongInfoFailure(t.message ?: "통신 오류")
            }
        })
    }
}