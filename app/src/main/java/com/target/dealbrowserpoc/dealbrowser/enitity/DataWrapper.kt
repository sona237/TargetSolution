package com.target.dealbrowserpoc.dealbrowser.enitity

import com.target.dealbrowserpoc.dealbrowser.network.NetworkResponse

class DataWrapper<T>(
    var data: MutableList<T>? =null,
    var networkStatus : NetworkResponse,
    var errorMessage : String? = null
)