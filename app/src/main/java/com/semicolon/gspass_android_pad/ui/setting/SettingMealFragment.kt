package com.semicolon.gspass_android_pad.ui.setting

import android.os.Bundle
import android.os.SharedMemory
import android.view.View
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.databinding.FragmentSettingMealBinding
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingMealViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingMealFragment : BaseFragment<FragmentSettingMealBinding>(R.layout.fragment_setting_meal) {

    private val vm:SettingMealViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
    }

    private fun checkIsElementSchool(){
       vm.gradeNumber.observe(viewLifecycleOwner,{

       })
    }

    private fun back(){
        vm.back.observe(viewLifecycleOwner,{
            val manager = requireActivity().supportFragmentManager
            manager.beginTransaction().replace(R.id.main_container,SettingFragment()).commit()
        })
    }


}