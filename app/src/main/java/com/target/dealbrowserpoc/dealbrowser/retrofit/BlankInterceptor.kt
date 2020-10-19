package com.target.dealbrowserpoc.dealbrowser.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**Blank interceptor for okHttp client for modes other than debug modes **/
class BlankInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return  chain.proceed(chain.request())
    }
}