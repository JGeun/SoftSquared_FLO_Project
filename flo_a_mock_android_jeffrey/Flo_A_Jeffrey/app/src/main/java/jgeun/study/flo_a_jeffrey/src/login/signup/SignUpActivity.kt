package jgeun.study.flo_a_jeffrey.src.login.signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivitySignupBinding
import jgeun.study.flo_a_jeffrey.src.login.signup.process.checklist.SignUpCheckListActivity

class SignUpActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate){
    private lateinit var mContext: Context
    private lateinit var mOAuthLoginModule: OAuthLogin
    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = applicationContext

        binding.signupIvBack.setOnClickListener{
            finish()
        }

        binding.signupRlEmailLogin.setOnClickListener{
            startActivity(Intent(this, SignUpCheckListActivity::class.java))
        }

        binding.signupRlNaverLogin.setOnClickListener{
            naverLogin()
        }

        binding.signupRlKakaoLogin.setOnClickListener{
            kakaoLogin()
        }
    }

    fun naverLogin() {
        mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(
            mContext,
            getString(R.string.naver_client_id),
            getString(R.string.naver_client_secret),
            getString(R.string.naver_client_name),
        )

        @SuppressLint("HandlerLeak")
        val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
            override fun run(success: Boolean) {
                if (success) {
                    val accessToken = mOAuthLoginModule.getAccessToken(mContext)
                    val refreshToken = mOAuthLoginModule.getRefreshToken(mContext)
                    val expiresAt = mOAuthLoginModule.getExpiresAt(mContext)
                    val tokenType = mOAuthLoginModule.getTokenType(mContext)

                    Log.i("LoginData", "accessToken : " + accessToken);
                    Log.i("LoginData", "refreshToken : " + refreshToken);
                    Log.i("LoginData", "expiresAt : " + expiresAt);
                    Log.i("LoginData", "tokenType : " + tokenType);
                } else {
                    val errorCode = mOAuthLoginModule.getLastErrorCode(mContext).code
                    val errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext)
                    showCustomToast("errorCode: " + errorCode + ", errorDesc: " + errorDesc)
                }
            }
        }
        mOAuthLoginModule.startOauthLoginActivity(this, mOAuthLoginHandler);
    }

    fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
            LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
}