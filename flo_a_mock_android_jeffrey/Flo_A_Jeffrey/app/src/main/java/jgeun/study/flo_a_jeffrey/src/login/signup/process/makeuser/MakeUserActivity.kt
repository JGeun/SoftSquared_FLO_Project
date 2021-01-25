package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityMakeUserBinding
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.MakeUserResponse
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.PostMakeUserRequest
import jgeun.study.flo_a_jeffrey.src.selecttaste.SelectTasteActivity

class MakeUserActivity : BaseActivity<ActivityMakeUserBinding>(ActivityMakeUserBinding::inflate), MakeUserView {
    private lateinit var tv_userName: TextView
    private lateinit var tv_userPhoneNumber: TextView
    private lateinit var et_email: EditText
    private lateinit var et_domain: EditText
    private lateinit var et_password: EditText
    private lateinit var et_verifyPassword: EditText
    private lateinit var et_birth: EditText

    private lateinit var iv_password: ImageView
    private lateinit var iv_verifyPassword: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv_userName = binding.makeUserTvUserName
        tv_userPhoneNumber = binding.makeUserTvUserPhoneNumber
        val getIntentFromVerify = intent
        val userName = getIntentFromVerify.getStringExtra("userName")
        if(!userName.equals("") && userName != null){
            tv_userName.setText(userName)
        }
        val userPhoneNumber = getIntentFromVerify.getStringExtra("userPhoneNumber")
        if(!userPhoneNumber.equals("") && userPhoneNumber != null){
            tv_userPhoneNumber.setText(userPhoneNumber)
        }

        et_email = binding.makeUserEtEmail
        et_domain = binding.makerUserEtDomain
        et_password = binding.makeUserEtPassword
        et_verifyPassword = binding.makeUserEtVerifyPassword
        et_birth = binding.makeUserEtUserBirth

        binding.makeUserIvBack.setOnClickListener {
            finish()
        }


        et_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(et_email.text.toString().length> 0){
                        binding.makverUserIvEmailClear.visibility = View.VISIBLE
                    }else {
                        binding.makverUserIvEmailClear.visibility = View.INVISIBLE
                    }
                if (isValidate()) {
                    binding.makeUserBtnSuccessMakeUser.isEnabled = true
                    binding.makeUserBtnSuccessMakeUser.setTextColor(resources.getColor(R.color.white))
                }
            }
        })

        binding.makverUserIvEmailClear.setOnClickListener{
            et_email.setText("")
            binding.makverUserIvEmailClear.visibility = View.INVISIBLE
        }

        et_domain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isValidate()) {
                    binding.makeUserBtnSuccessMakeUser.isEnabled = true
                    binding.makeUserBtnSuccessMakeUser.setTextColor(resources.getColor(R.color.white))
                }
            }
        })

        iv_password = binding.makerUserIvPassword
        iv_verifyPassword = binding.makerUserIvVerifyPassword

        iv_password.setOnClickListener {
            changeShowPassword(iv_password.getTag().toString())
        }
        iv_verifyPassword.setOnClickListener {
            changeShowPassword(iv_verifyPassword.getTag().toString())
        }

        binding.makeUserTlPassword.isHintAnimationEnabled =false
        binding.makeUserTlPassword.isHintEnabled =false

        et_password.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus)
                    binding.makeUserTlPassword.error = "영문 대문자/소문자/숫자/특수문자를 섞어 2가지 조합으로 최소 6~15자리로 입력해 주세요."
                else
                    binding.makeUserTlPassword.error = null
            }
        })
        et_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(et_password.length() > 0){
                    binding.makeUserIvPasswordClear.visibility = View.VISIBLE
                }else{
                    binding.makeUserIvPasswordClear.visibility = View.INVISIBLE
                }
                if (isValidate()) {
                    binding.makeUserBtnSuccessMakeUser.isEnabled = true
                    binding.makeUserBtnSuccessMakeUser.setTextColor(resources.getColor(R.color.white))
                }
            }
        })

        binding.makeUserIvPasswordClear.setOnClickListener{
            et_password.setText("")
            binding.makeUserIvPasswordClear.visibility = View.INVISIBLE
        }

        binding.makeUserTlVerifyPassword.isHintAnimationEnabled =false
        binding.makeUserTlVerifyPassword.isHintEnabled =false
        et_verifyPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(et_verifyPassword.length() > 0){
                    binding.makeUserIvVerifyPasswordClear.visibility = View.VISIBLE
                }else{
                    binding.makeUserIvVerifyPasswordClear.visibility = View.INVISIBLE
                }
                if (isValidate()) {
                    binding.makeUserBtnSuccessMakeUser.isEnabled = true
                    binding.makeUserBtnSuccessMakeUser.setTextColor(resources.getColor(R.color.white))
                }

                if(et_verifyPassword.length() > 0 && !et_verifyPassword.text.toString().equals(et_password.text.toString()))
                    binding.makeUserTlVerifyPassword.error = "앗! 비밀번호가 일치하지 않습니다."
                else
                    binding.makeUserTlVerifyPassword.error = null
            }
        })

        binding.makeUserIvVerifyPasswordClear.setOnClickListener{
            binding.makeUserIvVerifyPasswordClear.visibility = View.INVISIBLE
            et_verifyPassword.setText("")
        }

        et_birth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isValidate()) {
                    binding.makeUserBtnSuccessMakeUser.isEnabled = true
                    binding.makeUserBtnSuccessMakeUser.setTextColor(resources.getColor(R.color.white))
                }
            }
        })

        binding.makeUserBtnSuccessMakeUser.setOnClickListener {
            val name = tv_userName.text.toString()
            val phoneNo = tv_userPhoneNumber.text.toString()
            val email = et_email.text.toString() + "@" + et_domain.text.toString()
            val password = et_password.text.toString()
            val dateOfBirth = et_birth.text.toString()
            val postRequest = PostMakeUserRequest(name= name, phoneNo = phoneNo, email= email, password= password, dateOfBirth= dateOfBirth)
            showLoadingDialog(this)
            MakeUserService(this).tryPostMakeUser(postRequest)
        }
    }


    fun isValidate(): Boolean {
        val email = et_email.text.toString() + "@" + et_domain.text.toString()
        val password = et_password.text.toString()
        val verifyPassword = et_verifyPassword.text.toString()
        val birth = et_birth.text.toString()

        if (email.length >= 0 && password.length >= 0 && password.equals(verifyPassword) && birth.length == 6)
            return true
        else
            return false
    }

    fun changeShowPassword(tag: String) {
        if (tag.equals("1")) {
            iv_password.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password, null))
            iv_verifyPassword.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password, null))
            iv_password.setTag(2)
            iv_verifyPassword.setTag(2)
            et_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            et_password.setSelection(et_password.text.length);
            et_verifyPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            et_verifyPassword.setSelection(et_verifyPassword.text.length)
        } else {
            iv_password.setImageDrawable(resources.getDrawable(R.drawable.ic_not_show_password, null))
            iv_verifyPassword.setImageDrawable(resources.getDrawable(R.drawable.ic_not_show_password, null))
            iv_password.setTag(1)
            iv_verifyPassword.setTag(1)
            et_password.inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
            et_password.setSelection(et_password.text.length);
            et_verifyPassword.inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
            et_verifyPassword.setSelection(et_verifyPassword.text.length)
        }
    }

    override fun onPostMakeUserSuccess(response: MakeUserResponse) {
        dismissLoadingDialog()
        val code =response.code
        if(code == 1000){
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString(X_ACCESS_TOKEN, response.result.jwt)
            editor.putString(ApplicationClass.USER_EMAIL, et_email.text.toString() + "@" + et_domain.text.toString())
            editor.apply()

            val startSelectTasteIntent = Intent(this, SelectTasteActivity::class.java)
            startActivity(startSelectTasteIntent)
            finish()
        }
        else if(code == 2000)
            showCustomToast("잘못된 이름 형식")
        else if(code == 2001)
            showCustomToast("잘못된 휴대폰 번호 형식")
        else if(code == 2002)
            showCustomToast("잘못된 이메일 형식")
        else if(code == 2003)
            showCustomToast("잘못된 비밀번호 형식")
        else if(code == 2004)
            showCustomToast("잘못된 생년월일 형식")
        else if(code == 2005)
            showCustomToast("이미 사용중인 아이디입니다")
    }

    override fun onPostMakeUserFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}