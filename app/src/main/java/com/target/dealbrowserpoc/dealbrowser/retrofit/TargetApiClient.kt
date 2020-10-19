package com.target.dealbrowserpoc.dealbrowser.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.target.dealbrowserpoc.dealbrowser.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TargetApiClient {
        companion object {
        private var retrofit: Retrofit? = null
        private const val API_BASE_URL = "https://target-deals.herokuapp.com/api/"

        fun getClient(): TargetApiInterface {

            //Stetho Added to debug network request in chrome
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(if (BuildConfig.DEBUG) StethoInterceptor() else BlankInterceptor())
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return createInterface()
        }

        private fun createInterface(): TargetApiInterface {
            return retrofit!!.create(TargetApiInterface::class.java)
        }
    }
}