package com.target.dealbrowserpoc.dealbrowser.application

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.target.dealbrowserpoc.dealbrowser.BuildConfig
import com.target.dealbrowserpoc.dealbrowser.local.RealmConfig
import io.realm.Realm

class TargetApplication : Application() {
    private var realmConfig = RealmConfig()


    companion object{
        lateinit var context : Context

        fun realmInstance(): Realm {
            return Realm.getDefaultInstance()
        }
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
        context = applicationContext

        realmConfig.realmInitializer(this)
        realmInstance()
    }
}