package com.semicolon.gspass_android_pad.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.model.GetSchoolResponse

object AddSchoolAdapter {
    @BindingAdapter("schoolList")
    fun RecyclerView.bindSchoolList(schools: LiveData<ArrayList<GetSchoolResponse>>) {
        (adapter as GetSchoolsAdapter).setItems(schools)
    }

}