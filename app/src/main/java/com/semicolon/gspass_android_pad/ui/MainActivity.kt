package com.semicolon.gspass_android_pad.ui

import android.os.Bundle
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseActivity
import com.semicolon.gspass_android_pad.databinding.ActivityMainBinding
import com.semicolon.gspass_android_pad.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm : MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.checkLogin()
        observeNeedLogin()
    }

    private fun observeNeedLogin() {
        vm.needToLogin.observe(this,  {
            if (it) {
                startLogin()
                vm.needToLogin.value = false
            }
        })
    }

    private fun startLogin(){

    }
}