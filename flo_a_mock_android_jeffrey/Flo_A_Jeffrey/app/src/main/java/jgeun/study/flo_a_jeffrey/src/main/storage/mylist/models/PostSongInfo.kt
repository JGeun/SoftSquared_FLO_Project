package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models

import com.google.gson.annotations.SerializedName

data class PostSongInfo(
        @SerializedName("songInfo") val songInfo : ArrayList<ResultSongIdx>
)