package com.target.dealbrowserpoc.dealbrowser.application

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.target.dealbrowserpoc.dealbrowser.BuildConfig

class TargetApplication : Application() {

    companion object{
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
        context = applicationContext
    }
}