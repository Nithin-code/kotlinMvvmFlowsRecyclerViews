package com.nithin.kotlinmvvm.apiResponse

sealed class Response<T>(val status : Int?=null, val responseData : T? = null , val message : String? = null) {

    class Loading<T>(message: String) : Response<T>(message = message)


    class Success<T>(data : T) : Response<T>(status = 200, responseData = data, message = "Success")

    class Failure<T>(errorMessage : String) : Response<T>(message = errorMessage)

}