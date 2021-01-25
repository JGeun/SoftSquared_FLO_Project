package jgeun.study.flo_a_jeffrey.src.music

import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicRetrofitInterface {
    @GET("/songs/{songIdx}")
    fun getSongInfo(@Path("songIdx") songIdx: Int) : Call<MusicResponse>

}