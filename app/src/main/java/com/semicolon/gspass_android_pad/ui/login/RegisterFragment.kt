package com.semicolon.gspass_android_pad.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentRegisterBinding
import com.semicolon.gspass_android_pad.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val vm: RegisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeToast()
        observeInputData()
        observeFinish()
    }

    private fun observeInputData() {
        vm.userEmail.observe(viewLifecycleOwner, {
            vm.nEmptyEmail.value = !it.isNullOrBlank()
            checkDoneRegister()
        })

        vm.userName.observe(viewLifecycleOwner, {
            vm.nEmptyName.value = !it.isNullOrBlank()
            checkDoneRegister()
        })

        vm.userPassword.observe(viewLifecycleOwner, {
            vm.nEmptyPassword.value = !it.isNullOrBlank() && it.length > 7 && it.length < 21
            passwordErrorMessage()
            checkDoneRegister()
        })

        vm.userPasswordCheck.observe(viewLifecycleOwner, {
            vm.samePassword.value = vm.userPassword.value == vm.userPasswordCheck.value
            checkPasswordError()
            checkDoneRegister()
        })
    }

    private fun passwordErrorMessage() {
        if (vm.nEmptyPassword.value!!) {
            binding.makePasswordLayout.error = null
        } else {
            binding.makePasswordLayout.error = "8~20자리 사이의 비밀번호를 입력해주세요"
        }
    }

    private fun checkPasswordError() {
        if (vm.samePassword.value!!) {
            binding.checkPasswordLayout.error = null
        } else {
            binding.checkPasswordLayout.error = "비밀번호가 다릅니다"
        }
    }

    private fun checkDoneRegister() {
        vm.doneInput.value =
            vm.nEmptyEmail.value!! && vm.nEmptyName.value!! && vm.nEmptyPassword.value!! && vm.samePassword.value!!
    }

    private fun observeToast() {
        vm.toastMessage.observe(viewLifecycleOwner, { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeFinish(){
        vm.finishRegister.observe(viewLifecycleOwner, {
            if(it){
                finishRegister()
            }
        })
    }

    private fun finishRegister(){
        val fragment = requireActivity().supportFragmentManager
        val fragmentManager = fragment.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up)
        fragmentManager.replace(R.id.main_container,LoginFragment()).commit()
    }

}