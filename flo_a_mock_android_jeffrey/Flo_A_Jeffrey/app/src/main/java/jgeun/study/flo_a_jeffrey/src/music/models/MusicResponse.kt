package jgeun.study.flo_a_jeffrey.src.music.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeResult

data class MusicResponse(
        @SerializedName("result") val result: SongResult,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
        )