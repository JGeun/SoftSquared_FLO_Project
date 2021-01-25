package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify

import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.GetSignUpVerifyRequest
import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.SignUpVerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpVerifyRetrofitInterface {
    @GET("/overlap-users")
    fun getSignUpVerify(@Query("name") name: String, @Query("phoneNo") phoneNo:String): Call<SignUpVerifyResponse>
}