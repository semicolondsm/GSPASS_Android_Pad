package com.semicolon.gspass_android_pad.ui.setting

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        binding.lifecycleOwner = this
        binding.vm = vm
        vm.loadSettings()
        bindCheckBoxes()
        observeDuringTime()
        observeToast()
        editTime()
        back()
    }

    private fun bindCheckBoxes() {
        vm.breakFastChecked.observe(viewLifecycleOwner, {
            if (it&&vm.breakFastTime.value.isNullOrBlank()) {
                TimePickerDialog(activity, breakFastTimeDialogListener, 8, 0, false).show()
            }
            observeDoneInput()
        })
        vm.launchChecked.observe(viewLifecycleOwner, {
            if (it&&vm.launchTime.value.isNullOrBlank()) {
                TimePickerDialog(activity, launchTimeDialogListener, 12, 0, false).show()
            }
            observeDoneInput()
        })
        vm.dinnerChecked.observe(viewLifecycleOwner, {
            if (it&&vm.dinnerTime.value.isNullOrBlank()) {
                TimePickerDialog(activity, dinnerTimeDialogListener, 18, 0, false).show()
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
            (vm.breakFastChecked.value!! || vm.launchChecked.value!! || vm.dinnerChecked.value!!) && !vm.duringTime.value.isNullOrBlank()
    }

    private fun observeToast() {
        vm.toastMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun editTime() {
        vm.breakFastEdit.observe(viewLifecycleOwner, {
            if (it&&vm.breakFastTime.value.isNullOrBlank()) {
                val timePicker = TimePickerDialog(activity, breakFastTimeDialogListener, 8, 0, false)
                timePicker.setTitle("아침신청시간")
                timePicker.show()
                vm.breakFastEdit.value = false
            }
        })
        vm.launchEdit.observe(viewLifecycleOwner, {
            if (it&&vm.launchTime.value.isNullOrBlank()) {
                val timePicker = TimePickerDialog(activity, launchTimeDialogListener, 8, 0, false)
                timePicker.setTitle("점심신청시간")
                timePicker.show()
                vm.launchEdit.value = false
            }
        })
        vm.dinnerEdit.observe(viewLifecycleOwner, {
            if (it&&vm.dinnerTime.value.isNullOrBlank()) {
                val timePicker = TimePickerDialog(activity, dinnerTimeDialogListener, 8, 0, false)
                timePicker.setTitle("저녁신청시간")
                timePicker.show()
                vm.dinnerEdit.value = false
            }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private val breakFastTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.breakFastTime.value = SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.breakFastTimeView.value = SimpleDateFormat("HH시mm분").format(calendar.time)
        }

    @SuppressLint("SimpleDateFormat")
    private val launchTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.launchTime.value = SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.launchTimeView.value = SimpleDateFormat("HH시mm분").format(calendar.time)
        }

    @SuppressLint("SimpleDateFormat")
    private val dinnerTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            vm.dinnerTime.value = SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.dinnerTimeView.value = SimpleDateFormat("HH시mm분").format(calendar.time)
        }

    private fun back(){
        vm.back.observe(viewLifecycleOwner,{
            if(it){
                val manager = requireActivity().supportFragmentManager
                manager.beginTransaction().replace(R.id.main_container,SettingFragment()).commit()
            }
        })
    }

}