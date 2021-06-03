package com.semicolon.gspass_android_pad.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.model.GetSchoolResponse

object AddSchoolAdapter {
    @JvmStatic
    @BindingAdapter("schoolList")
    fun bindSchoolList(recyclerView: RecyclerView,schools: LiveData<List<GetSchoolResponse>>) {
        (recyclerView.adapter as GetSchoolsAdapter?)?.setItems(schools)
    }

}