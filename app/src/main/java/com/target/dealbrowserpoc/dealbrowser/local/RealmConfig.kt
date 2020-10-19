package com.target.dealbrowserpoc.dealbrowser.local

import android.app.Application
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmConfig {
    private val REALM_CONFIG_NAME = "target.realm"

    companion object {
        const val REALM_SCHEMA_VERSION: Long = 0
    }

    fun realmInitializer(application: Application) {
        Realm.init(application)

        val realmConfig = RealmConfiguration.Builder()
            .name(REALM_CONFIG_NAME)
            .schemaVersion(REALM_SCHEMA_VERSION)
            .migration(TargetRealmMigration())
            .build()

        Realm.setDefaultConfiguration(realmConfig)

        Stetho.initialize(
            Stetho.newInitializerBuilder(application)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(application).build()).build()
        )
    }
}

private const val REALM_SYNC_CONFIG_NAME = "target.realmSync"
private const val REALM_SYNC_SCHEMA_VERSION: Long = 1

val realmConfigSync: RealmConfiguration = RealmConfiguration.Builder()
    .name(REALM_SYNC_CONFIG_NAME)
    .schemaVersion(REALM_SYNC_SCHEMA_VERSION)
    .deleteRealmIfMigrationNeeded()
    .build()

fun getSyncRealm(): Realm? {
    return Realm.getInstance(realmConfigSync)
}
