package com.target.dealbrowserpoc.dealbrowser.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TargetApiClient {

   /* private var retrofit: Retrofit? = null
    private val BASE_URL = " https://target-deals.herokuapp.com/api"

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }*/


    companion object {
        var retrofit: Retrofit? = null
        private val API_BASE_URL = "https://target-deals.herokuapp.com/api/"

        fun getClient(): TargetApiInterface {


            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return createInterface()
        }

        private fun createInterface(): TargetApiInterface {
            return retrofit!!.create(TargetApiInterface::class.java)
        }
    }
}