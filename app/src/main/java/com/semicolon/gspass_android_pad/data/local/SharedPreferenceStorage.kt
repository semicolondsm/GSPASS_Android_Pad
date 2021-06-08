package com.semicolon.gspass_android_pad.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceStorage(private val context: Context) {
    private var pref: SharedPreferences? = null

    fun getInfo(content: String?): String{
        if(pref == null)pref = context.getSharedPreferences(content, Context.MODE_PRIVATE)
        return if (content == "token"){
            "Bearer " + pref!!.getString(content,"")
        } else
            pref!!.getString(content, "").toString()
    }

    fun getInfo(content: String?,default:Boolean): Boolean{
        if(pref == null)pref = context.getSharedPreferences(content, Context.MODE_PRIVATE)
        return if (content == "token"){
            pref!!.getBoolean(content,default)
        } else
            pref!!.getBoolean(content, default)
    }

    fun saveInfo(info: String, content: String){
        if(pref == null) pref = context.getSharedPreferences(content, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString(content,info)
        editor.apply()
    }

    fun saveInfo(info: Boolean, content: String){
        if(pref == null) pref = context.getSharedPreferences(content, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putBoolean(content,info)
        editor.apply()
    }

    fun clearToken(content: String){
        if(pref == null) pref = context.getSharedPreferences(content, Context.MODE_PRIVATE)
        pref!!.edit().remove(content).apply()
    }
}