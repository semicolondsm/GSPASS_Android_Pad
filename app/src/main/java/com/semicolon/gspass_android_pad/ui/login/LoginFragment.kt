package com.semicolon.gspass_android_pad.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentLoginBinding
import com.semicolon.gspass_android_pad.ui.MainActivity
import com.semicolon.gspass_android_pad.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val vm: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        observeNeedRegister()
        observeInput()
        observeToast()
        observeDone()
    }

    private fun observeNeedRegister() {
        vm.needRegister.observe(viewLifecycleOwner, {
            if (it) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
                    ?.replace(R.id.main_container, RegisterFragment())?.commit()
            }
        })
    }

    private fun observeInput() {
        vm.userId.observe(viewLifecycleOwner, {
            vm.emailDone.value = !it.isNullOrBlank()
            checkDone()
        })
        vm.userPassword.observe(viewLifecycleOwner, {
            vm.passwordDone.value = !it.isNullOrBlank()
            checkDone()
        })

    }

    private fun checkDone() {
        vm.doneInput.value = vm.emailDone.value!! && vm.passwordDone.value!!
    }

    private fun observeToast() {
        vm.toastMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeDone() {
        vm.doneLogin.observe(viewLifecycleOwner, {
            if(it){
                (activity as MainActivity).vm.doneToken.value = true
            }
        })
    }
}