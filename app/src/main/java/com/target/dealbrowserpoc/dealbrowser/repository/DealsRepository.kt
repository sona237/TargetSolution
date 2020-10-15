package com.target.dealbrowserpoc.dealbrowser.repository

import androidx.lifecycle.MutableLiveData
import com.target.dealbrowserpoc.dealbrowser.enitity.DataWrapper
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.network.DealsNetworkRequest
import com.target.dealbrowserpoc.dealbrowser.network.NetworkInterface

class DealsRepository {
    private val mDealsNetworkRequest = DealsNetworkRequest

    companion object {
        val instance = DealsRepository()
    }

   /* fun getAllDeals(): LiveData<DealsListLiveData> {
        return DealsNetworkRequest.getAllDeals()
    }*/

    fun fetchAllDeals(liveData : MutableLiveData<DataWrapper<Deal>>){
        mDealsNetworkRequest.fetchAllDeals(object :
            NetworkInterface<Deal> {
            override fun onRequestSuccess(data: MutableList<Deal>) {

                liveData.value = DataWrapper(
                    data = data
                )
            }

            override fun onRequestFailure(responseCode: Int, uiMessage: String) {
                liveData.value =
                    DataWrapper(
                        data = liveData.value?.data,
                        errorMessage = uiMessage
                    )
            }
        })

    }

}