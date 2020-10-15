package com.target.dealbrowserpoc.dealbrowser.liveDataResponses

import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.network.NetworkResponse

data class DealsListLiveData(val dealDetailsList: List<Deal>?,
                             val networkResponse : NetworkResponse)