package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.target.dealbrowserpoc.dealbrowser.R

class DealsListActivity : AppCompatActivity() {

    private lateinit var transaction: FragmentTransaction
   private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_list)
        mFragmentManager = supportFragmentManager
        transaction = mFragmentManager.beginTransaction().add(R.id.dealsListContainer,DealsListFragment.newInstance())
        transaction.commit()

    }

   /* private fun startDealsListFragment() {
        fragment = OrderDetailsFragment.newInstance(this)
        addFragmentIfNotAdded(
            R.id.orderContainer,
            fragment,
            ORDER_DETAILS_FRAGMENT_TAG,
            addToBackStack = false,
            slideInRight = true
        )
    }*/

}
