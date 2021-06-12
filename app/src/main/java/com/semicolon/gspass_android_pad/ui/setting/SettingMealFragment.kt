package com.semicolon.gspass_android_pad.ui.setting

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentSettingMealBinding
import com.semicolon.gspass_android_pad.model.GradeMealData
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingMealViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class SettingMealFragment :
    BaseFragment<FragmentSettingMealBinding>(R.layout.fragment_setting_meal) {

    private val vm: SettingMealViewModel by viewModel()

    private var grade = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        vm.loadSchoolType()
        observeToast(vm.toastMessage)
        editGrade()
        back()
    }

    private fun editGrade() {
        vm.editGrade.observe(viewLifecycleOwner, {
            grade = it
            if (vm.breakFastCheck.value!!) {
                val timePicker =
                    TimePickerDialog(activity, breakfastTimeDialogListener, 8, 0, false)
                timePicker.setTitle("$grade 학년의 아침배식")
                timePicker.setMessage("배식시작시간을 정해주세요")
                timePicker.show()
            }
            if (vm.lunchCheck.value!!) {
                val timePicker = TimePickerDialog(activity, lunchTimeDialogListener, 12, 0, false)
                timePicker.setTitle("$grade 학년의 점심배식")
                timePicker.setMessage("배식시작시간을 정해주세요")
                timePicker.show()
            }
            if (vm.dinnerCheck.value!!) {
                val timePicker = TimePickerDialog(activity, dinnerTimeDialogListener, 16, 0, false)
                timePicker.setTitle("$grade 학년의 저녁배식")
                timePicker.setMessage("배식시작시간을 정해주세요")
                timePicker.show()
            }

        })
    }

    private fun back() {
        vm.back.observe(viewLifecycleOwner, {
            if (it) {
                val manager = requireActivity().supportFragmentManager
                manager.beginTransaction().replace(R.id.main_container, SettingFragment()).commit()
            }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private val breakfastTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            (vm.gradeMeals.value?.get(grade)?: GradeMealData("00:00:00","00:00:00","00:00:00")).breakfast =
                SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.sendMealData(grade)
        }

    @SuppressLint("SimpleDateFormat")
    private val lunchTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            (vm.gradeMeals.value?.get(grade)?: GradeMealData("00:00:00","00:00:00","00:00:00")).lunch =
                SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.sendMealData(grade)
        }

    @SuppressLint("SimpleDateFormat")
    private val dinnerTimeDialogListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            (vm.gradeMeals.value?.get(grade)?: GradeMealData("00:00:00","00:00:00","00:00:00")).dinner =
                SimpleDateFormat("HH:mm:00").format(calendar.time)
            vm.sendMealData(grade)
        }

}