package com.target.dealbrowserpoc.dealbrowser.local

import android.util.Log
import com.target.dealbrowserpoc.dealbrowser.utils.TargetLogger
import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema

class TargetRealmMigration : RealmMigration {
    private var realmSchema: RealmSchema? = null
    private var oldVersion: Int = 0

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        realmSchema = realm.schema
        Log.e("Migration", "old version $oldVersion currVer $newVersion")
        this.oldVersion = oldVersion.toInt()
        checkMigrationNeeded(this.oldVersion)
    }

    private fun checkMigrationNeeded(oldVersion: Int) {
        TargetLogger.info("Migration $oldVersion")
        when(oldVersion){

        }
    }


}