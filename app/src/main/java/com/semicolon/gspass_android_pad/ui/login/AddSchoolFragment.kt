package com.semicolon.gspass_android_pad.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentAddSchoolBinding
import com.semicolon.gspass_android_pad.viewmodel.AddSchoolViewModel
import io.reactivex.rxjava3.disposables.Disposable
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

    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    
    private fun observeInputText(){

    }
}