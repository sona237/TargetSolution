package com.target.dealbrowserpoc.dealbrowser.network

interface NetworkInterface<T> {
        fun onRequestSuccess(data: MutableList<T>)
        fun onRequestFailure(responseCode: Int, uiMessage: String)
}