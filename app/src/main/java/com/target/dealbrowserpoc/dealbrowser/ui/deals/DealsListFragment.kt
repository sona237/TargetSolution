package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.enitity.Deal
import com.target.dealbrowserpoc.dealbrowser.enitity.DealsNavigationSteps
import com.target.dealbrowserpoc.dealbrowser.network.NetworkResponse
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModel
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModelFactory
import kotlinx.android.synthetic.main.fragment_deals_list.*

class DealsListFragment : Fragment() {
    private val dealsViewModelFactory = DealsViewModelFactory()
    private val dealsViewModel: DealsViewModel by activityViewModels { dealsViewModelFactory }
    private var dealsListAdapter = DealsListAdapter()


    companion object{
        fun newInstance(): DealsListFragment {
            return DealsListFragment()
        }
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
        observeDealsList()
        setupList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchAllDeals()
    }

    private fun setupList() {
        rvDealsList.adapter = dealsListAdapter
            .setContext(requireActivity())
            .setOnDealSelected { deal, i ->
                onDealsListItemSelected(deal)
            }
        rvDealsList.setHasFixedSize(true)
        rvDealsList.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

    }

    private fun onDealsListItemSelected(deal: Deal) {
        dealsViewModel.mSelectedDeal.value = deal
        dealsViewModel.dealNavigationStep.value = DealsNavigationSteps.DEAL_SELECTED
    }

    private fun fetchAllDeals(){
        dealsViewModel.fetchAllDeals()
    }

    private fun observeDealsList(){
        dealsViewModel.dealsListLiveData.observe(viewLifecycleOwner, Observer {

            var deals = it.data

            when (it.networkStatus) {
                NetworkResponse.WAIT -> {
                    isLoading(true)
                }
                NetworkResponse.OFFLINE -> {
                    isLoading(false)
                    Toast.makeText(context,it.errorMessage,Toast.LENGTH_LONG).show()
                }
                NetworkResponse.SUCCEED -> {
                    isLoading(false)
                    if (deals?.size!! > 0) {
                        requireActivity().runOnUiThread {
                            dealsListAdapter.setData(deals, false)
                        }
                    }else{
                        Toast.makeText(context,getString(R.string.noDealsFound),Toast.LENGTH_LONG).show()
                    }
                }
                NetworkResponse.FAILED -> {
                    isLoading(false)
                    Toast.makeText(context,it.errorMessage,Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun isLoading(isVisible: Boolean) {
        startShimmer(shimmerFrameLayout, isVisible)
    }

    private fun startShimmer(mShimmerLayout: ShimmerFrameLayout?, isLoading: Boolean) {
        if (mShimmerLayout == null) return
        if (isLoading) {
            mShimmerLayout.visibility = View.VISIBLE
            mShimmerLayout.startShimmer()
        } else {
            mShimmerLayout.visibility = View.GONE
            mShimmerLayout.stopShimmer()
        }
    }
}