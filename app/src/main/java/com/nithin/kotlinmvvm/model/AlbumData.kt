package com.nithin.kotlinmvvm.model

import com.google.gson.annotations.SerializedName

data class AlbumData(
    @SerializedName("albumId")
    val albumId : Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("url")
    val url : String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl : String
)
