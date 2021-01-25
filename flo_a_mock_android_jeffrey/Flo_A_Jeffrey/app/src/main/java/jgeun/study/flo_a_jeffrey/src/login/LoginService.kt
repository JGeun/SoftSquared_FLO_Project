package jgeun.study.flo_a_jeffrey.src.login

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginInfo
import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginActivity) {
    fun tryPostUserLoginInfo(postUserLoginInfo: PostUserLoginInfo){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postUserLoginInfo(postUserLoginInfo).enqueue(object : Callback<PostUserLoginResponse>{
            override fun onResponse(call: Call<PostUserLoginResponse>, response: Response<PostUserLoginResponse>) {
                view.onGetLoginInfoSuccess(response.body() as PostUserLoginResponse)
            }

            override fun onFailure(call: Call<PostUserLoginResponse>, t: Throwable) {
                view.onGetUserInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}