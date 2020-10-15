package com.target.dealbrowserpoc.dealbrowser.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.target.dealbrowserpoc.dealbrowser.BuildConfig

class TargetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
    }
}