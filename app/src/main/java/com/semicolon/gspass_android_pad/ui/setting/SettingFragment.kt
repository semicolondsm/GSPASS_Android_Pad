package com.semicolon.gspass_android_pad.ui.setting

import android.os.Bundle
import android.view.View
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentSettingBinding
import com.semicolon.gspass_android_pad.ui.MainActivity
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val vm: SettingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        vm.loadSettings()
        observeApplySetting()
        observeMealSetting()
        startQrCode()
    }

    private fun observeApplySetting(){
        vm.startApplySetting.observe(viewLifecycleOwner,{
            if(it){
                startApplySetting()
            }
        })
    }

    private fun startApplySetting(){
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(R.id.main_container,SettingApplyFragment()).commit()
    }

    private fun observeMealSetting(){
        vm.startMealSetting.observe(viewLifecycleOwner,{
            if(it){
                startMealSetting()
            }
        })
    }

    private fun startMealSetting(){
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(R.id.main_container,SettingMealFragment()).commit()
    }

    private fun startQrCode(){
        vm.finishSetting.observe(viewLifecycleOwner,{
            if(it){
                (activity as MainActivity).startQrCode()
            }
        })
    }

}