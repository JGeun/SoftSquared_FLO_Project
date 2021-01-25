package jgeun.study.flo_a_jeffrey.src.login.signup.process.checklist

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivitySignupCheckListBinding
import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.SignUpVerifyActivity

class SignUpCheckListActivity : BaseActivity<ActivitySignupCheckListBinding>(ActivitySignupCheckListBinding::inflate){
    private var rbCheckArrayList = ArrayList<Boolean>()
    private var rbArrayList = ArrayList<RadioButton>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.checkListIvBack.setOnClickListener{
            finish()
        }
        rbArrayList.add(binding.checkListRb1)
        rbArrayList.add(binding.checkListRb2)
        rbArrayList.add(binding.checkListRb3)
        rbArrayList.add(binding.checkListRb4)
        rbArrayList.add(binding.checkListRb5)
        rbArrayList.add(binding.checkListRbAll)

        for(i in 0.. 6)
            rbCheckArrayList.add(false)
        checkRb()

        if(rbArrayList.get(0).isChecked && rbArrayList.get(1).isChecked && rbArrayList.get(2).isChecked) {
            binding.checkListBtnNextActivity.setTextColor(resources.getColor(R.color.white))
            binding.checkListBtnNextActivity.isEnabled = true
        }

        binding.checkListBtnNextActivity.setOnClickListener{
            startActivity(Intent(this, SignUpVerifyActivity::class.java))
            finish()
        }
    }
    fun checkRb(){
        for(i in 0..5){
            val rb = rbArrayList.get(i)
            rb.setOnClickListener{
                if(!rbCheckArrayList.get(i)){
                    rb.isChecked = true
                    rbCheckArrayList.set(i, true)
                    if(isMeetBtnEnabled()){
                        binding.checkListBtnNextActivity.setTextColor(resources.getColor(R.color.white))
                        binding.checkListBtnNextActivity.isEnabled = true
                    }
                }else {
                    rb.isChecked = false
                    rbCheckArrayList.set(i, false)
                    if(!isMeetBtnEnabled()) {
                        binding.checkListBtnNextActivity.setTextColor(resources.getColor(R.color.floForBtnDisabledTextColor))
                        binding.checkListBtnNextActivity.isEnabled = false
                    }
                }
            }
        }
        val rb = rbArrayList.get(5)
        rb.setOnClickListener{
            if(!rbCheckArrayList.get(5)){
                for(i in 0..5) {
                    rbArrayList.get(i).isChecked = true
                    rbCheckArrayList.set(i, true)
                }
                rb.isChecked = true
                rbCheckArrayList.set(5, true)
                binding.checkListBtnNextActivity.setTextColor(resources.getColor(R.color.white))
                binding.checkListBtnNextActivity.isEnabled = true
            }else{
                for(i in 0..5) {
                    rbArrayList.get(i).isChecked = false
                    rbCheckArrayList.set(i, false)
                }
                rb.isChecked = false
                rbCheckArrayList.set(5, false)
                binding.checkListBtnNextActivity.setTextColor(resources.getColor(R.color.floForBtnDisabledTextColor))
                binding.checkListBtnNextActivity.isEnabled = false
            }
        }
    }

    fun isMeetBtnEnabled(): Boolean{
        if(rbArrayList.get(0).isChecked && rbArrayList.get(1).isChecked && rbArrayList.get(2).isChecked) {
            return true
        }else
            return false
    }


}