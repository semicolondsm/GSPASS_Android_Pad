package com.semicolon.gspass_android_pad.ui.setting

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentSettingApplyBinding
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SettingApplyFragment :
    BaseFragment<FragmentSettingApplyBinding>(R.layout.fragment_setting_apply) {

    private val vm: SettingApplyViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadSettings()
        bindCheckBoxes()
        observeDuringTime()
    }

    private fun bindCheckBoxes() {
        vm.breakFastChecked.observe(viewLifecycleOwner,{
            if(it){
                TimePickerDialog(activity,breakFastTimeDialogListener,8,0,false).show()
            }
            observeDoneInput()
        })
        vm.launchChecked.observe(viewLifecycleOwner,{
            if(it){
                TimePickerDialog(activity,launchTimeDialogListener,12,0,false).show()
            }
            observeDoneInput()
        })
        vm.dinnerChecked.observe(viewLifecycleOwner,{
            if(it){
                TimePickerDialog(activity,dinnerTimeDialogListener,18,0,false).show()
            }
            observeDoneInput()
        })
    }

    private fun observeDuringTime() {
        vm.duringTime.observe(viewLifecycleOwner, {
            observeDoneInput()
        })
    }

    private fun observeDoneInput() {
        vm.doneSetting.value =
            vm.breakFastChecked.value!! || vm.launchChecked.value!! || vm.dinnerChecked.value!! && vm.duringTime.value.isNullOrBlank()
    }

    @SuppressLint("SimpleDateFormat")
    private val breakFastTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.breakFastTime.value = SimpleDateFormat("HH:mm:ss").format(calendar.time)
        }

    @SuppressLint("SimpleDateFormat")
    private val launchTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.launchTime.value = SimpleDateFormat("HH:mm:ss").format(calendar.time)
        }
    @SuppressLint("SimpleDateFormat")
    private val dinnerTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.dinnerTime.value = SimpleDateFormat("HH:mm:ss").format(calendar.time)
        }


}