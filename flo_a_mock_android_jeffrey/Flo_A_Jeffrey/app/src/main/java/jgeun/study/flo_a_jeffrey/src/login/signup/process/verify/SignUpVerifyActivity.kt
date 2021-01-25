package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.RelativeLayout.ALIGN_PARENT_RIGHT
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivitySignupVerifyBinding
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.MakeUserActivity
import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.SignUpVerifyResponse
import java.util.*

class SignUpVerifyActivity : BaseActivity<ActivitySignupVerifyBinding>(ActivitySignupVerifyBinding::inflate),
        SignUpVerifyView {
    private lateinit var et_userName: EditText
    private lateinit var iv_userNameClear: ImageView
    private lateinit var et_userPhoneNumber: EditText
    private lateinit var iv_userPhoneNumberClear: ImageView
    //private var tempReqeustVerifyNumber = 1000

    val SMS_RECEIVE_PERMISSON = 1
    private val MY_PERMISSION_REQUEST_SMS = 1001

    var certiNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_userName = binding.verifyEtUserName
        iv_userNameClear = binding.verifyIvUserNameClear
        et_userPhoneNumber = binding.verifyEtUserPhoneNumber
        iv_userPhoneNumberClear = binding.verifyIvNumberClear

        binding.verifyIvBack.setOnClickListener {
            finish()
        }

        binding.verifyTlUserName.isHintAnimationEnabled = false
        binding.verifyTlUserName.isHintEnabled = false
        et_userName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length: Int = et_userName.text.toString().length
                if (length > 0)
                    iv_userNameClear.visibility = View.VISIBLE
                else
                    iv_userNameClear.visibility = View.INVISIBLE
                if (length > 5)
                    binding.verifyTlUserName.error = resources.getString(R.string.verify_name_error)
                else
                    binding.verifyTlUserName.error = null

                if (isValidate()) {
                    binding.verifyBtnSuccessVerification.setTextColor(resources.getColor(R.color.white))
                    binding.verifyBtnSuccessVerification.isEnabled = true
                }
            }
        })
        iv_userNameClear.setOnClickListener{
            et_userName.setText("")
            iv_userNameClear.visibility = View.INVISIBLE
        }

        et_userPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length: Int = et_userPhoneNumber.text.length
                if (length > 0)
                    iv_userPhoneNumberClear.visibility = View.VISIBLE
                else
                    iv_userPhoneNumberClear.visibility = View.INVISIBLE
                if (length >= 9)
                    binding.verifyBtnSendSMS.isEnabled = true
                else
                    binding.verifyBtnSendSMS.isEnabled = false
            }
        })
        iv_userPhoneNumberClear.setOnClickListener{
            et_userPhoneNumber.setText("")
            iv_userPhoneNumberClear.visibility = View.INVISIBLE
        }

        binding.verifyBtnSendSMS.setOnClickListener {
            checkSendSMSPermission()
            checkReceiveSMSPermission()
            sendCertiNumber()

            binding.verifyRlReceiveSMSCode.visibility = View.VISIBLE
            binding.verifyBtnSendSMS.setText(resources.getString(R.string.verify_resendSMS))
            val layoutParams = RelativeLayout.LayoutParams(180, 70)
            layoutParams.setMargins(0, 25, 0, 0)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
            binding.verifyBtnSendSMS.layoutParams =layoutParams

            binding.verifyBtnResendAnim.animation = AnimationUtils.loadAnimation(this, R.anim.slowly_fadein)
            binding.verifyBtnResendAnim.animation = AnimationUtils.loadAnimation(this, R.anim.slowly_fadeout)

            binding.verifyEtSmsCode.setText(certiNumber.toString())
            if (isValidate()) {
                binding.verifyBtnSuccessVerification.setTextColor(resources.getColor(R.color.white))
                binding.verifyBtnSuccessVerification.isEnabled = true
            }
        }

        binding.verifyBtnSuccessVerification.setOnClickListener {
            val name = et_userName.text.toString()
            val phoneNumber = et_userPhoneNumber.text.toString()
            showLoadingDialog(this)
            SignUpVerifyService(this).tryGetSignUpVerify(name, phoneNumber)
        }
    }

    fun isValidate(): Boolean {
        if (et_userName.text.toString().length >= 1 && et_userName.text.toString().length <= 4 && binding.verifyEtSmsCode.text.toString().equals(certiNumber.toString())) {
            return true
        } else
            return false
    }

    override fun onGetSignUpVerifySuccess(response: SignUpVerifyResponse) {
        dismissLoadingDialog()
        if(response.code == 1000){
            val startMakeUserIntent = Intent(this, MakeUserActivity::class.java)
            startMakeUserIntent.putExtra("userName", et_userName.text.toString())
            startMakeUserIntent.putExtra("userPhoneNumber", et_userPhoneNumber.text.toString())
            startActivity(startMakeUserIntent)
            finish()
        }
        else if(response.code == 2000)
            showCustomToast("현재 존재하는 회원입니다")
        else if(response.code == 2001)
            showCustomToast("이름을 다시 입력해주세요")
        else
            showCustomToast("번호를 제대로 입력해주세요")
    }

    override fun onGetSignUpVerifyFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류: $message")
    }

    private fun sendCertiNumber() {
        val random = Random()
        certiNumber = random.nextInt(9999 - 1111 + 1) + 1111
        val phoneNumber: String = et_userPhoneNumber.text.toString()
        val message = "<#>[FLO] 인증번호는 [$certiNumber]입니다. mNne/dvPJK8"
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(applicationContext, "전송 성공", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "전송 실패", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun checkSendSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("info")
                builder.setMessage("This app won't work properly unless you grant SMS permission")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setNeutralButton("OK") { dialog, which -> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), MY_PERMISSION_REQUEST_SMS) }
                val dialog = builder.create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), MY_PERMISSION_REQUEST_SMS)
            }
        }
    }

    private fun checkReceiveSMSPermission() { // Here, thisActivity is the current activity
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "SMS 권한없음")
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),
                        SMS_RECEIVE_PERMISSON)
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),
                        SMS_RECEIVE_PERMISSON)
            }
        } else {
            Log.d("TAG", "SMS 권한있음")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            SMS_RECEIVE_PERMISSON -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "SMS권한 승인함", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "SMS권한 거부함", Toast.LENGTH_SHORT).show()
            }
        }
    }


}