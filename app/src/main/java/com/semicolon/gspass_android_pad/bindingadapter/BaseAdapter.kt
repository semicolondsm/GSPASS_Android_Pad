package com.semicolon.gspass_android_pad.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object BaseAdapter {
    @BindingAdapter("adapter")
    fun adapter(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>){
        val layoutManager= LinearLayoutManager(recyclerView.context)
        layoutManager.orientation=RecyclerView.VERTICAL
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter = adapter
    }
}