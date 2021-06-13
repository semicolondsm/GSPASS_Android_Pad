package com.semicolon.gspass_android_pad.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class BaseFragment<B:ViewDataBinding>(
    @LayoutRes private val layoutRes:Int
):Fragment() {
    protected lateinit var binding:B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }
    fun observeToast(message:LiveData<String>){
        message.observe(viewLifecycleOwner,{
            Toast.makeText(activity,it,Toast.LENGTH_SHORT).show()
        })
    }

}