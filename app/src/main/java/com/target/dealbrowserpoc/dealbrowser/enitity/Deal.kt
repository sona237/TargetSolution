package com.target.dealbrowserpoc.dealbrowser.enitity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Deal (
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var imageUrl: String? = null,
    @SerializedName("salePrice") var salePrice: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("guid") var guid: String? = null,
    @SerializedName("aisle") var aisle: String? = null,
    @SerializedName("_id") var _id: String? = null,
    @SerializedName("index") var index : Int? =0,
    var viewType: Int = 1
    ):Serializable
