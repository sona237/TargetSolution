package com.target.dealbrowserpoc.dealbrowser.local

import com.target.dealbrowserpoc.dealbrowser.application.TargetApplication
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.utils.TargetLogger
import com.target.dealbrowserpoc.dealbrowser.utils.save
import io.realm.Realm
import io.realm.RealmQuery

object DealsLocalData {
    fun saveOrderList(orderList: List<Deal>) {
        orderList.save(Realm.getDefaultInstance())
    }

    fun getAllDeals() : MutableList<Deal>? {
        var dealsList: List<Deal>? = null
        val realm = TargetApplication.realmInstance()
        realm.executeTransaction { realm ->
            val query: RealmQuery<Deal> = realm.where(Deal::class.java)
            val result = query.findAll()
            dealsList = realm.copyFromRealm(result)
            TargetLogger.debug("Deals list size in db  ${(dealsList as MutableList<Deal>?)?.size}")
        }

        return dealsList?.toMutableList()
    }

}