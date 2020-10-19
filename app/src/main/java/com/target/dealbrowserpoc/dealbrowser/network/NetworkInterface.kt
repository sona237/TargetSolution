package com.target.dealbrowserpoc.dealbrowser.network

/**
 * Interface to handle success and failure responses
 */
interface NetworkInterface<T> {
        fun onRequestSuccess(data: MutableList<T>)
        fun onRequestFailure(responseCode: Int, uiMessage: String)
}