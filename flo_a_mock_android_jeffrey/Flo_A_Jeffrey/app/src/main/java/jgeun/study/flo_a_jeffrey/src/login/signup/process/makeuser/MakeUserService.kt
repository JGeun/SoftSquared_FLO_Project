package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.MakeUserResponse
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.PostMakeUserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeUserService(val view:MakeUserActivity) {

    fun tryPostMakeUser(postMakeUserRequest: PostMakeUserRequest){
        val makeUserRetrofitInterface = ApplicationClass.sRetrofit.create(MakeUserRetrofitInterface::class.java)
        makeUserRetrofitInterface.postMakeUser(postMakeUserRequest).enqueue(object: Callback<MakeUserResponse>{
            override fun onResponse(call: Call<MakeUserResponse>, response: Response<MakeUserResponse>) {
                view.onPostMakeUserSuccess(response.body() as MakeUserResponse)
            }

            override fun onFailure(call: Call<MakeUserResponse>, t: Throwable) {
                view.onPostMakeUserFailure(t.message?: "통신 오류")
            }
        })
    }
}