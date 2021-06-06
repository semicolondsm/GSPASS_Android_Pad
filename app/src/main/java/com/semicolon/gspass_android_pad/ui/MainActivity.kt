package com.semicolon.gspass_android_pad.ui

import android.os.Bundle
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseActivity
import com.semicolon.gspass_android_pad.databinding.ActivityMainBinding
import com.semicolon.gspass_android_pad.ui.login.AddSchoolFragment
import com.semicolon.gspass_android_pad.ui.login.LoginFragment
import com.semicolon.gspass_android_pad.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.checkLogin()
        observeNeedLogin()
    }

    private fun observeNeedLogin() {
        vm.needToLogin.observe(this, {
            if (it) {
                startLogin()
                vm.needToLogin.value = false
            }
        })
    }

    private val loginFragment = LoginFragment()
    private val addSchoolFragment = AddSchoolFragment()

    private fun startLogin() {
        supportFragmentManager.beginTransaction().add(R.id.main_container, addSchoolFragment)
            .commit()
    }
}