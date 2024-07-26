package com.nithin.kotlinmvvm.application

import android.app.Application
import android.app.Dialog
import android.content.Context
import com.nithin.kotlinmvvm.R
import com.nithin.kotlinmvvm.client.ApiInterface
import com.nithin.kotlinmvvm.client.RetrofitClient

class MyApplication : Application() {

    companion object{
        var mAPIInterface : ApiInterface?=null
            private set

        val getApiInterface : () -> ApiInterface? = {
            if(mAPIInterface == null){
                mAPIInterface = RetrofitClient.retrofitLamda.invoke().create(ApiInterface::class.java)
            }
            mAPIInterface
        }

        var progressDialog : Dialog? = null
            private set

        lateinit var myApplicationContext : Context
    }


    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
        createApiInterface()
        createProgressDialog()
    }

    private fun createProgressDialog() {
        if (progressDialog == null){
            progressDialog = Dialog(this)
            progressDialog?.setContentView(R.layout.progress_dialog)
        }
    }



    private fun createApiInterface() {
        if(mAPIInterface == null){
            mAPIInterface = RetrofitClient.getClient().create(ApiInterface::class.java)
        }
    }




}