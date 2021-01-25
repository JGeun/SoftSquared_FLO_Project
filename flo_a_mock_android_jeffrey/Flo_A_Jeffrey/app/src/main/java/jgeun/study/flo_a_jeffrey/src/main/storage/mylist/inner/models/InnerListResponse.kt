package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.ResultMakeFolder

data class InnerListResponse(
        @SerializedName("result") val result: ArrayList<ResultList>,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)