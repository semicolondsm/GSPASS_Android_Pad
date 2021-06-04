package com.semicolon.gspass_android_pad.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentAddSchoolBinding
import com.semicolon.gspass_android_pad.viewmodel.AddSchoolViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class AddSchoolFragment : BaseFragment<FragmentAddSchoolBinding>(R.layout.fragment_add_school) {

    private val vm: AddSchoolViewModel by viewModel()

    private val adapter = GetSchoolsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        binding.vm = vm
        observeInputText()
    }

    private val textSource = Observable.create<String> {
        binding.schoolGetEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                it.onNext(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    lateinit var observer: Disposable
    private fun observeInputText() {
        observer = textSource.debounce(500, TimeUnit.MILLISECONDS).subscribe {
            vm.loadSchools(it)
        }
    }

    override fun onDetach() {
        super.onDetach()
        observer.dispose()
    }
}