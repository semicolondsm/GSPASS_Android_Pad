package com.semicolon.gspass_android_pad.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.model.GetSchoolResponse

@BindingAdapter("schoolList")
fun RecyclerView.bindSchoolList(schools: MutableLiveData<ArrayList<GetSchoolResponse>>){
    (adapter as GetSchoolsAdapter).setItems(schools)
}