package com.semicolon.gspass_android_pad.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData

abstract class BaseActivity <B : ViewDataBinding>(
    @LayoutRes private val layoutResId:Int
) : AppCompatActivity(){

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutResId)
    }

    fun observeToastMessage(message:LiveData<String>){
        message.observe(this,{
            Toast.makeText(this, it,Toast.LENGTH_SHORT).show()
        })

    }

}
