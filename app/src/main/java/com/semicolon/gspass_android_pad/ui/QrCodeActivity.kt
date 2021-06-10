package com.semicolon.gspass_android_pad.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.google.zxing.integration.android.IntentIntegrator
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseActivity
import com.semicolon.gspass_android_pad.databinding.ActivityQrCodeBinding
import com.semicolon.gspass_android_pad.viewmodel.QrViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QrCodeActivity : BaseActivity<ActivityQrCodeBinding>(R.layout.activity_qr_code) {

    private val vm: QrViewModel by viewModel()
    private val intentIntegrator = IntentIntegrator(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        intentIntegrator.setOrientationLocked(false)
        intentIntegrator.setPrompt("급식패스를 사각형 안에 인식시켜주세요")
        intentIntegrator.setCameraId(1)
        intentIntegrator.initiateScan()
    }

    override fun onResume() {
        super.onResume()
        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        vm.token.value = "Bearer " + result.contents
        vm.checkQr()
    }
}