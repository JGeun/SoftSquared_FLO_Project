package jgeun.study.flo_a_jeffrey.src.login

import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginInfo
import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/logins")
    fun postUserLoginInfo(@Body params: PostUserLoginInfo) : Call<PostUserLoginResponse>
}