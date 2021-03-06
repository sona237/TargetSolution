package com.target.dealbrowserpoc.dealbrowser.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.dealbrowserpoc.dealbrowser.enitity.DataWrapper
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.enitity.DealsNavigationSteps
import com.target.dealbrowserpoc.dealbrowser.repository.DealsRepository

class DealsViewModel : ViewModel() {
    private var mDealsRepository: DealsRepository? = DealsRepository.instance

    var mSelectedDeal = MutableLiveData<Deal>()
    val dealNavigationStep = MutableLiveData<DealsNavigationSteps>()

    val dealsListLiveData: MutableLiveData<DataWrapper<Deal>> by lazy {
        MutableLiveData<DataWrapper<Deal>>()
    }

    fun fetchAllDeals(){
        return mDealsRepository?.fetchAllDeals(dealsListLiveData)!!
    }

}