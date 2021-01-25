package jgeun.study.flo_a_jeffrey.src.music.songinfo

import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import jgeun.study.flo_a_jeffrey.src.music.songinfo.models.SongInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SongInfoRetrofitInterface {
    @GET("/songs/{songIdx}")
    fun getSongInfo(@Path("songIdx") songIdx: Int) : Call<SongInfoResponse>
}