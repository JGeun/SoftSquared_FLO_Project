package jgeun.study.flo_a_jeffrey.src.music.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeResult

data class SongResult(
        @SerializedName("songContents") val songContents: String,
        @SerializedName("songLength") val songLength: String,
        @SerializedName("songLyrics") val songLyrics: String,
        @SerializedName("songLikedInfo") val songLikedInfo: String,
        @SerializedName("albumTitle") val albumTitle: String,
        @SerializedName("lyricist") val lyricist: String,
        @SerializedName("composer") val composer: String,
        @SerializedName("arranger") val arranger: String,
)