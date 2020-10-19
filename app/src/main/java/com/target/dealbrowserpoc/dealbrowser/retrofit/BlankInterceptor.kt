package com.target.dealbrowserpoc.dealbrowser.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class BlankInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return  chain.proceed(chain.request())
    }
}