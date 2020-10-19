package com.target.dealbrowserpoc.dealbrowser.network

import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.application.TargetApplication
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.responses.ServerResponse
import com.target.dealbrowserpoc.dealbrowser.retrofit.TargetApiClient
import com.target.dealbrowserpoc.dealbrowser.utils.TargetLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DealsNetworkRequest {

    fun fetchAllDeals(networkInterface: NetworkInterface<Deal>) {
        val networkCall = TargetApiClient.getClient().getAllDeals()

        networkCall?.enqueue(object : Callback<ServerResponse<Deal>> {
                override fun onFailure(call: Call<ServerResponse<Deal>>, t: Throwable) {
                    networkInterface.onRequestFailure(0, TargetApplication.context.getString(R.string.errorOccurred))
                    TargetLogger.error(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ServerResponse<Deal>>,
                    response: Response<ServerResponse<Deal>>
                ) {
                    if (response.isSuccessful) {
                            networkInterface.onRequestSuccess(response.body()?.data!!)
                    } else {
                        networkInterface.onRequestFailure(response.code(), response.message())
                    }
                }
            })
    }

}