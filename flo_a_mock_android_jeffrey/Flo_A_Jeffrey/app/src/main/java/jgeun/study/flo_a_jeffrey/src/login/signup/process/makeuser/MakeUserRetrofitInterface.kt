package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser

import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.MakeUserResponse
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.PostMakeUserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MakeUserRetrofitInterface {
    @POST("/users")
    fun postMakeUser(@Body params: PostMakeUserRequest): Call<MakeUserResponse>
}