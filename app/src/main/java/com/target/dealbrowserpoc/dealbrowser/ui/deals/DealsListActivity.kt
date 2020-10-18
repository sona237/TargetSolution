package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.enitity.DealsNavigationSteps
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModel
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModelFactory
import kotlinx.android.synthetic.main.activity_deals_list.*

class DealsListActivity : AppCompatActivity() {

    private lateinit var transaction: FragmentTransaction
    private lateinit var mFragmentManager: FragmentManager
    private val dealsViewModelFactory = DealsViewModelFactory()
    private val dealsViewModel: DealsViewModel by viewModels { dealsViewModelFactory }

    companion object{
        const val DEALS_LIST_FRAGMENT = "DealsListFragment"
        const val DEAL_DETAILS_FRAGMENT = "DealDetailsFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_list)
        setSupportActionBar(app_toolbar)

        observeNavigationSteps()

        mFragmentManager = supportFragmentManager
        transaction = mFragmentManager.beginTransaction().add(R.id.dealsListContainer,DealsListFragment.newInstance())
        transaction.commit()

    }

    private fun observeNavigationSteps(){
        dealsViewModel.dealNavigationStep.observe(this, Observer{
            if(it == DealsNavigationSteps.DEAL_SELECTED){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.dealsListContainer, DealDetailsFragment.newInstance(), DEAL_DETAILS_FRAGMENT)
                transaction.addToBackStack(DEAL_DETAILS_FRAGMENT)
                transaction.commit()
            }
        })
    }

}
