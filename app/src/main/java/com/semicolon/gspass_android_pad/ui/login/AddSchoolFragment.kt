package com.semicolon.gspass_android_pad.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentAddSchoolBinding
import com.semicolon.gspass_android_pad.viewmodel.AddSchoolViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddSchoolFragment : BaseFragment<FragmentAddSchoolBinding>(R.layout.fragment_add_school) {

    private val vm: AddSchoolViewModel by viewModel()

    private val adapter = GetSchoolsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        binding.vm = vm
    }
}