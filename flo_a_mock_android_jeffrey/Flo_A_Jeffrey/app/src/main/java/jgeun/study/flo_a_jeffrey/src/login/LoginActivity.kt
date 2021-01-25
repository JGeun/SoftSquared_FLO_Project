package jgeun.study.flo_a_jeffrey.src.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputLayout
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.USER_EMAIL
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.sSharedPreferences
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.config.UserPlayList
import jgeun.study.flo_a_jeffrey.databinding.ActivityLoginBinding
import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginInfo
import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginResponse
import jgeun.study.flo_a_jeffrey.src.login.signup.SignUpActivity
import jgeun.study.flo_a_jeffrey.src.main.MainActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView {
    private lateinit var mContext: Context
    private lateinit var mOAuthLoginModule: OAuthLogin
    private val TAG = "LoginActivity"

    private lateinit var et_email: EditText
    private lateinit var iv_emailClear: ImageView
    private lateinit var il_domain: TextInputLayout
    private lateinit var et_domain: EditText
    private lateinit var il_password: TextInputLayout
    private lateinit var et_password: EditText
    private lateinit var iv_showPassword: ImageView
    private lateinit var iv_passwordClear: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = applicationContext
        binding.loginBtnExit.setOnClickListener {
            finish()
        }

        et_email = binding.loginEtUserEmail
        iv_emailClear = binding.loginIvEmailClear
        et_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_email.text.length > 0) {
                    iv_emailClear.visibility = View.VISIBLE
                } else
                    iv_emailClear.visibility = View.INVISIBLE

                if(validation()){
                    binding.loginBtnEmailLogin.isEnabled = true
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                }else{
                    binding.loginBtnEmailLogin.isEnabled = false
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.floForBtnDisabledTextColor, null))
                }
            }
        })

        binding.loginIvEmailClear.setOnClickListener{
            et_email.setText("")
            binding.loginIvEmailClear.visibility = View.INVISIBLE
        }

        il_domain = binding.loginIlDomain
        et_domain = il_domain.editText!!
        il_domain.isHintAnimationEnabled = false
        il_domain.isHintEnabled = false
        et_domain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if(validation()){
                    binding.loginBtnEmailLogin.isEnabled = true
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                }else{
                    binding.loginBtnEmailLogin.isEnabled = false
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.floForBtnDisabledTextColor, null))
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        et_domain.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus){
                    et_email.isActivated = true
                }else
                    et_email.isActivated = false
            }
        })

        il_password = binding.loginIlPassword
        et_password = il_password.editText!!
        il_password.isHintAnimationEnabled = false
        il_password.isHintEnabled = false

        iv_passwordClear = binding.loginIvPasswordClear
        iv_showPassword = binding.loginIvShowPassword
        et_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_password.text.length > 0) {
                    iv_passwordClear.visibility = View.VISIBLE
                } else
                    iv_passwordClear.visibility = View.INVISIBLE

                if(validation()){
                    binding.loginBtnEmailLogin.isEnabled = true
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                }else{
                    binding.loginBtnEmailLogin.isEnabled = false
                    binding.loginBtnEmailLogin.setTextColor(ResourcesCompat.getColor(resources, R.color.floForBtnDisabledTextColor, null))
                }
            }
        })
        iv_passwordClear.setOnClickListener {
            et_password.setText("")
            iv_passwordClear.visibility = View.INVISIBLE
        }


        iv_showPassword.setOnClickListener {
            if (iv_showPassword.getTag() == 1) {
                iv_showPassword.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password, null))
                iv_showPassword.setTag(2)
                et_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                et_password.setSelection(et_password.text.length);
            } else {
                iv_showPassword.setImageDrawable(resources.getDrawable(R.drawable.ic_not_show_password, null))
                iv_showPassword.setTag(1)
                et_password.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
                et_password.setSelection(et_password.text.length);
            }
        }

        binding.loginIvNaverLogin.setOnClickListener {
            naverLogin()
        }

        binding.loginIvKakaoLogin.setOnClickListener {
            kakaoLogin()
        }
        binding.loginTvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        binding.loginBtnEmailLogin.setOnClickListener {
            val email = et_email.text.toString() + "@" + et_domain.text.toString()
            val password = et_password.text.toString()

//            val editor = sSharedPreferences.edit()
//            editor.putString(X_ACCESS_TOKEN, null)
//            editor.apply()

            showLoadingDialog(this)
            LoginService(this).tryPostUserLoginInfo(PostUserLoginInfo(email, password))
        }
    }
    fun validation() : Boolean{
        if(et_email.length() > 0 && et_domain.length() > 0 && et_password.length() > 0)
            return true
        else
            return false
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

    override fun onGetLoginInfoSuccess(respose: PostUserLoginResponse) {
        dismissLoadingDialog()
        if(respose.code == 1000){
            val editor = sSharedPreferences.edit()
            editor.putString(X_ACCESS_TOKEN, respose.result.jwt)
            editor.putString(USER_EMAIL, et_email.text.toString() + "@" + et_domain.text.toString())
            editor.apply()

            Log.d("Login", respose.result.jwt)

            val serverPlayList = respose.result.playlist
            for(playList in serverPlayList)
                ApplicationClass.userMusicPlayList.add(UserPlayList(playList.songIdx, playList.songTitle, playList.songImageUrl, playList.artistName))
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }else if(respose.code == 2000){
            showCustomToast("로그인 정보가 없습니다")
        }else if(respose.code == 2001){
            showCustomToast("잘못된 이메일 형식입니다")
        }else if(respose.code == 2002){
            showCustomToast("잘못된 비밀번호 형식입니다.")
        }else if(respose.code == 2003){
            showCustomToast("존재하지 않는 이메일 정보입니다.")
        }else{
            showCustomToast("비밀번호 정보가 잘못되었습니다.")
        }
    }

    override fun onGetUserInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}