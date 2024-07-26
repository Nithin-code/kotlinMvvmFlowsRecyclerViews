package com.nithin.kotlinmvvm.client

import com.nithin.kotlinmvvm.apiResponse.Response
import com.nithin.kotlinmvvm.model.AlbumData
import com.nithin.kotlinmvvm.model.ApiData
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    suspend fun getAlbumDetails() : List<AlbumData>

}