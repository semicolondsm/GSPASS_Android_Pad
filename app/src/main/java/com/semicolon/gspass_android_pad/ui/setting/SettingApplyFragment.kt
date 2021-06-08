package com.semicolon.gspass_android_pad.ui.setting

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentSettingApplyBinding
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingApplyFragment : BaseFragment<FragmentSettingApplyBinding>(R.layout.fragment_setting_apply) {

    private val vm:SettingApplyViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCheckBoxes()
    }

    private fun bindCheckBoxes(){
        binding.settingABreakCb.checkedChanges().subscribe{
            vm.breakFastChecked.value = it
        }

    }
}