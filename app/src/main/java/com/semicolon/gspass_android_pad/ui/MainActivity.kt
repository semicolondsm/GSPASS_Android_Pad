package com.semicolon.gspass_android_pad.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseActivity
import com.semicolon.gspass_android_pad.databinding.ActivityMainBinding
import com.semicolon.gspass_android_pad.ui.login.AddSchoolFragment
import com.semicolon.gspass_android_pad.ui.login.LoginFragment
import com.semicolon.gspass_android_pad.ui.setting.SettingFragment
import com.semicolon.gspass_android_pad.viewmodel.MainViewModel
import com.semicolon.gspass_android_pad.viewmodel.QrViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.checkSchool()
        observeNeedGetSchool()
        observeNeedLogin()
        startSetting()
        observeToastMessage(vm.toastMessage)
    }

    private fun observeNeedGetSchool() {
        vm.needToGetSchool.observe(this, {
            if (it) {
                startAddSchool()
                vm.needToGetSchool.value = false
            }
        })
    }

    private fun observeNeedLogin() {
        vm.needToLogin.observe(this, {
            if (it) {
                startLogin()
                vm.needToLogin.value = false
            }
        })
    }

    private val addSchoolFragment = AddSchoolFragment()
    private val loginFragment = LoginFragment()
    private val settingFragment = SettingFragment()

    private fun startAddSchool() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, addSchoolFragment)
            .commit()
    }

    private fun startLogin() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, loginFragment)
            .commit()
    }

    private fun startSetting() {
        vm.doneToken.observe(this, {
            if (it) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, settingFragment)
                    .commit()
            }
        })
    }

    fun startQrCode(){
        val intent = Intent(this,QrCodeActivity::class.java)
        startActivity(intent)
    }

}