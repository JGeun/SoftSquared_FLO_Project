package jgeun.study.flo_a_jeffrey.src.music.songinfo.models

import com.google.gson.annotations.SerializedName

data class SongInfoResponse(
        @SerializedName("result") val result: SongInfoResult,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
        )