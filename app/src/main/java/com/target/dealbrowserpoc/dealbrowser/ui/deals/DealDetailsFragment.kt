package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.utils.TargetLogger
import com.target.dealbrowserpoc.dealbrowser.utils.loadImageUrl
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModel
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModelFactory
import kotlinx.android.synthetic.main.fragment_deal_details.*

/*
* Fragment to to show details of a deal. This fragment is loaded when a deal is selected from deals list fragment
*/

class DealDetailsFragment : Fragment() {
    private val dealsViewModelFactory = DealsViewModelFactory()
    private val dealsViewModel: DealsViewModel by activityViewModels { dealsViewModelFactory }

    companion object{
        fun newInstance(): DealDetailsFragment {
            return DealDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as DealsListActivity).supportActionBar?.title = getString(R.string.dealDetails)
        }
        setUpView()
    }

    private fun setUpView(){
        tvPrice.text = dealsViewModel.mSelectedDeal.value?.price

        val salePrice = dealsViewModel.mSelectedDeal.value?.salePrice

        //if sale price is null, set actual price in sale price text view and do not strike the actual price text
        if(salePrice == null){
            tvSalePrice.text = dealsViewModel.mSelectedDeal.value?.price

        }else{
            tvSalePrice.text = dealsViewModel.mSelectedDeal.value?.salePrice
            tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        tvTitle.text = dealsViewModel.mSelectedDeal.value?.title
        tvDescription.text = dealsViewModel.mSelectedDeal.value?.description
        try {
            itemImage.loadImageUrl(
                dealsViewModel.mSelectedDeal.value?.imageUrl ?: "",
                placeholder = R.drawable.image_not_available
            )

        } catch (e: Exception) {
            TargetLogger.error(e.localizedMessage)
        }
    }
}