package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models

import com.google.gson.annotations.SerializedName

data class PostMusicInListResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)