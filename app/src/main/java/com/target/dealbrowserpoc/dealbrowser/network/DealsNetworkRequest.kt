package com.target.dealbrowserpoc.dealbrowser.network

import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.responses.ServerResponse
import com.target.dealbrowserpoc.dealbrowser.retrofit.TargetApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DealsNetworkRequest {

/*
        fun getAllDeals() : MutableLiveData<DealsListLiveData> {
            val dataResponse = MutableLiveData<DealsListLiveData>()

            dataResponse.value = DealsListLiveData(null,NetworkResponse.WAIT)

            val networkCall = TargetApiClient.getClient().getAllDeals()
            networkCall.enqueue(object : Callback<ServerResponse<DealDetails>> {

                override fun onResponse(
                    call: Call<ServerResponse<DealDetails>>,
                    response: Response<ServerResponse<DealDetails>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        dataResponse.value = DealsListLiveData(
                            data,
                            NetworkResponse.SUCCEED
                        )
                    } else {
                        dataResponse.value = DealsListLiveData(
                            null,
                            NetworkResponse.OFFLINE
                        )
                    }
                }

                override fun onFailure(call: Call<ServerResponse<DealDetails>>, t: Throwable) {
                    dataResponse.value = DealsListLiveData(
                        null,
                        NetworkResponse.OFFLINE
                    )
                }

            })

            return dataResponse

        }
*/








    fun fetchAllDeals(networkInterface: NetworkInterface<Deal>) {
        val networkCall = TargetApiClient.getClient().getAllDeals()

        networkCall?.enqueue(object : Callback<ServerResponse<Deal>> {
                override fun onFailure(call: Call<ServerResponse<Deal>>, t: Throwable) {
                    networkInterface.onRequestFailure(0, t.localizedMessage)
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