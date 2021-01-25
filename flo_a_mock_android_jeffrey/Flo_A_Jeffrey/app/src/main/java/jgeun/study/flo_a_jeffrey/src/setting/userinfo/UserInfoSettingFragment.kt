package jgeun.study.flo_a_jeffrey.src.setting.userinfo

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentUserInfoSettingBinding
import jgeun.study.flo_a_jeffrey.src.setting.SettingFragment


class UserInfoSettingFragment : BaseFragment<FragmentUserInfoSettingBinding>(FragmentUserInfoSettingBinding::bind, R.layout.fragment_user_info_setting) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userInfoSettingIvExit.setOnClickListener{
            val settingFragmentManager = activity!!.supportFragmentManager
            settingFragmentManager.beginTransaction().replace(R.id.main_frm, SettingFragment()).commitAllowingStateLoss()
        }
        binding.userInfoSettingRlLogout.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setNegativeButton("취소") {
                dialog, id -> showCustomToast("")

            }
            builder.setPositiveButton("확인") {
                dialog, id ->
                val editor = ApplicationClass.sSharedPreferences.edit()
                editor.putString(ApplicationClass.X_ACCESS_TOKEN, null)
                editor.apply()

                val settingFragmentManager = activity!!.supportFragmentManager
                settingFragmentManager.beginTransaction().replace(R.id.main_frm, SettingFragment()).commitAllowingStateLoss()

                showCustomToast("로그아웃 되었습니다.")

            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}
