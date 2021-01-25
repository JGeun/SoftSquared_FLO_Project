package jgeun.study.flo_a_jeffrey.src.music.songinfo

import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import jgeun.study.flo_a_jeffrey.src.music.songinfo.models.SongInfoResponse


interface SongInfoView {
    fun onGetSongInfoSuccess(response: SongInfoResponse)
    fun onGetSongInfoFailure(message: String)
}