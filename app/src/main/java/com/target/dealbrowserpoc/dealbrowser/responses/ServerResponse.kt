package com.target.dealbrowserpoc.dealbrowser.responses

import com.google.gson.annotations.SerializedName

data class ServerResponse<T> (
    @SerializedName("_id") var _id: String,
    @SerializedName("data") var data: MutableList<T>,
    @SerializedName("type") var type: String?
)