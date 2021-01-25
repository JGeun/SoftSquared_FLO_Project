package jgeun.study.flo_a_jeffrey.src.music

import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse


interface MusicView {
    fun onGetMusicInfoSuccess(response: MusicResponse)
    fun onGetMusicInfoFailure(message: String)
}