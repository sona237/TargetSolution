package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModel
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModelFactory

class DealsListFragment : Fragment() {
    private lateinit var dealsViewModel : DealsViewModel
    private lateinit var dealsViewModelFactory : DealsViewModelFactory

    companion object{
        fun newInstance(): DealsListFragment {
            return DealsListFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deals_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealsViewModelFactory = DealsViewModelFactory()
        dealsViewModel = ViewModelProvider(this,dealsViewModelFactory).get(DealsViewModel::class.java)
        observeDealsList()
    }

    override fun onResume() {
        super.onResume()
        fetchAllDeals()
    }

    private fun fetchAllDeals(){
        dealsViewModel.fetchAllDeals()
    }

    private fun observeDealsList(){
        dealsViewModel.dealsListLiveData.observe(viewLifecycleOwner, Observer {
            setData(it.data)
        })
    }

    private fun setData(dealsList : MutableList<Deal>?){
        println("Size of list ${dealsList?.size}")

        dealsList?.forEach {
            println("${it.title} && ${it.salePrice}")
        }

    }
}