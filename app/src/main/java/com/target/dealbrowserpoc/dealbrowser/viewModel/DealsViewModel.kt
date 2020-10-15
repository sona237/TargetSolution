package com.target.dealbrowserpoc.dealbrowser.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.dealbrowserpoc.dealbrowser.enitity.DataWrapper
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.repository.DealsRepository

class DealsViewModel : ViewModel() {
    private var mDealsRepository: DealsRepository? = DealsRepository.instance
    val dealsListLiveData: MutableLiveData<DataWrapper<Deal>> by lazy {
        MutableLiveData<DataWrapper<Deal>>()
    }

    /*fun getAllDeals(): LiveData<DealsListLiveData>? {
        return mDealsRepository?.getAllDeals()
    }*/

    fun fetchAllDeals(){
        return mDealsRepository?.fetchAllDeals(dealsListLiveData)!!
    }

}