package com.semicolon.gspass_android_pad.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.widget.textChanges
import com.semicolon.gspass_android_pad.R
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.base.BaseFragment
import com.semicolon.gspass_android_pad.databinding.FragmentAddSchoolBinding
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import com.semicolon.gspass_android_pad.viewmodel.login.AddSchoolViewModel
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class AddSchoolFragment : BaseFragment<FragmentAddSchoolBinding>(R.layout.fragment_add_school) {

    private val vm: AddSchoolViewModel by inject()

    private lateinit var adapter: GetSchoolsAdapter
    private lateinit var dialog: AlertDialog.Builder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        vm.loadSchools("")
        val setLayoutManager = LinearLayoutManager(context)
        setLayoutManager.orientation = RecyclerView.VERTICAL
        adapter = GetSchoolsAdapter(vm)
        binding.schoolGetRv.layoutManager = setLayoutManager
        binding.schoolGetRv.adapter = adapter
        dialog = AlertDialog.Builder(context, R.style.myDialog)
        observeInputText(binding.schoolGetEt)
        observeChooseSchool()
    }

    private lateinit var observer: Disposable

    private fun observeInputText(textView: EditText) {
        observer =
            textView.textChanges().debounce(500, TimeUnit.MILLISECONDS).subscribe {
                vm.loadSchools(it.toString())
            }
    }

    private fun observeChooseSchool() {
        vm.chooseSchool.observe(viewLifecycleOwner, {
            if (it != null) {
                showDialog(it)

            }
        })
    }

    private fun showDialog(school: GetSchoolResponse) {
        dialog.setTitle("확인해주세요")
            .setMessage("${school.name}(이)가 맞습니까?")
            .setPositiveButton("네") { _, _ ->
                startLogin(school)
            }.setNegativeButton("아니요") { _, _ ->
                Toast.makeText(context, "학교를 다시 선택해주세요", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun startLogin(school: GetSchoolResponse) {
        vm.postSchool(school)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, LoginFragment())?.commit()
    }


    override fun onDetach() {
        super.onDetach()
        observer.dispose()
    }
}
