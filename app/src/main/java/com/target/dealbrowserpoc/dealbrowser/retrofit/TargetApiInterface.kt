package com.target.dealbrowserpoc.dealbrowser.retrofit

import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.responses.ServerResponse
import retrofit2.Call
import retrofit2.http.GET

interface TargetApiInterface {

    @GET("deals")
    fun getAllDeals(): Call<ServerResponse<Deal>>?

}