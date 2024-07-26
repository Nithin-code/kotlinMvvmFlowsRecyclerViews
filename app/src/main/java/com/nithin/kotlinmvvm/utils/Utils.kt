package com.nithin.kotlinmvvm.utils

import android.widget.Toast
import com.nithin.kotlinmvvm.application.MyApplication

object Utils {


    fun showToast(message : String){
        Toast.makeText(
            MyApplication.myApplicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun validateToast(message: String) : Boolean{
        return message.isNotEmpty()
    }

}