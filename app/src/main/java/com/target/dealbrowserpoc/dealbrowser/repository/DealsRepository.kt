package com.target.dealbrowserpoc.dealbrowser.repository

import androidx.lifecycle.MutableLiveData
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.application.TargetApplication
import com.target.dealbrowserpoc.dealbrowser.enitity.DataWrapper
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.network.DealsNetworkRequest
import com.target.dealbrowserpoc.dealbrowser.network.NetworkInterface
import com.target.dealbrowserpoc.dealbrowser.network.NetworkResponse
import com.target.dealbrowserpoc.dealbrowser.utils.Helper

class DealsRepository {
    private val mDealsNetworkRequest = DealsNetworkRequest

    companion object {
        val instance = DealsRepository()
    }

   /* fun getAllDeals(): LiveData<DealsListLiveData> {
        return DealsNetworkRequest.getAllDeals()
    }*/

    fun fetchAllDeals(liveData : MutableLiveData<DataWrapper<Deal>>){
        liveData.value = DataWrapper(
            data = null,
            networkStatus = NetworkResponse.WAIT
        )

        if(!Helper.isInternetConnected(TargetApplication.context)){
            liveData.value = DataWrapper(
                data= null,
                networkStatus = NetworkResponse.OFFLINE,
                errorMessage = TargetApplication.context.getString(R.string.noNetwork)
            )
            return
        }
        mDealsNetworkRequest.fetchAllDeals(object :
            NetworkInterface<Deal> {
            override fun onRequestSuccess(data: MutableList<Deal>) {

                liveData.value = DataWrapper(
                    data = data,
                    networkStatus = NetworkResponse.SUCCEED
                )
            }

            override fun onRequestFailure(responseCode: Int, uiMessage: String) {
                liveData.value =
                    DataWrapper(
                        data = liveData.value?.data,
                        networkStatus = NetworkResponse.FAILED,
                        errorMessage = uiMessage
                    )
            }
        })

    }

}