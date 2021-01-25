package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models

import com.google.gson.annotations.SerializedName

data class MakeFolderResponse(
        @SerializedName("result") val result: ResultMakeFolder,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)