package jgeun.study.flo_a_jeffrey.src.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentSettingBinding
import jgeun.study.flo_a_jeffrey.src.login.LoginActivity
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.home.HomeFragment
import jgeun.study.flo_a_jeffrey.src.selecttaste.SelectTasteActivity
import jgeun.study.flo_a_jeffrey.src.setting.userinfo.UserInfoSettingFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        val jwtToken = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)
        val userEmail = ApplicationClass.sSharedPreferences.getString(ApplicationClass.USER_EMAIL, null)

        if(userEmail!= null)
            binding.settingTvUserEmail.setText(userEmail)

        if(jwtToken != null){
            binding.settingLoginAfter.visibility = View.VISIBLE
            binding.settingLoginBefore.visibility =View.INVISIBLE
            binding.settingLlUserInfo.visibility = View.VISIBLE
            binding.settingRlVoiceService.visibility = View.VISIBLE
            binding.settingLine.visibility = View.VISIBLE
        }else{
            binding.settingLoginAfter.visibility = View.INVISIBLE
            binding.settingLoginBefore.visibility =View.VISIBLE
            binding.settingLlUserInfo.visibility = View.GONE
            binding.settingRlVoiceService.visibility = View.GONE
            binding.settingLine.visibility = View.GONE
        }

        binding.settingBtnUserInfo.setOnClickListener{
            val settingFragmentManager = activity!!.supportFragmentManager
            settingFragmentManager.beginTransaction().replace(R.id.main_frm, UserInfoSettingFragment()).commitAllowingStateLoss()
        }

        binding.settingIvExit.setOnClickListener{
            val settingFragmentManager = activity!!.supportFragmentManager
            settingFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        }

        binding.settingBtnLogin.setOnClickListener{
            startActivity(Intent(context, LoginActivity::class.java))
        }

        binding.settingBtnUserSeletTaste.setOnClickListener{
            startActivity(Intent(context, SelectTasteActivity::class.java))
        }
    }
}