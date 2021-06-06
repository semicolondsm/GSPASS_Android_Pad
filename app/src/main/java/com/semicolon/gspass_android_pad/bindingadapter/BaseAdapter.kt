package com.semicolon.gspass_android_pad.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun RecyclerView.adapter(setAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    adapter = setAdapter
}