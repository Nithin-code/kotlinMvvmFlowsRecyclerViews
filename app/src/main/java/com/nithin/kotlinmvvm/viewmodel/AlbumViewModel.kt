package com.nithin.kotlinmvvm.viewmodel

import android.icu.text.Transliterator.Position
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithin.kotlinmvvm.apiResponse.Response
import com.nithin.kotlinmvvm.application.MyApplication
import com.nithin.kotlinmvvm.model.AlbumData
import com.nithin.kotlinmvvm.model.ApiData
import com.nithin.kotlinmvvm.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel(){

    var _myApiResponse : MutableStateFlow<Response<List<AlbumData>>> = MutableStateFlow(Response.Loading("Loading"))
        private set

    private val mApiInference = MyApplication.getApiInterface.invoke()



    init {
        callAlbumApi()
    }

    private fun callAlbumApi() {
        viewModelScope.launch {
            try {
                val data = mApiInference?.getAlbumDetails()
                if (data == null){
                    _myApiResponse.emit(Response.Failure("UnExpected Error"))
                }else{
                    _myApiResponse.emit(Response.Success(data))
                }
            }catch (t : Exception){
                _myApiResponse.emit(Response.Failure("${t.message}"))
            }
        }
    }


    fun cardClick(position: Int){
        val url = _myApiResponse.value.responseData?.get(position)?.thumbnailUrl
        Utils.showToast(url.toString())
    }




}