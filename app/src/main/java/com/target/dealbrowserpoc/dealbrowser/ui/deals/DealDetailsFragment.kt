package com.target.dealbrowserpoc.dealbrowser.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModel
import com.target.dealbrowserpoc.dealbrowser.viewModel.DealsViewModelFactory

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
        println("${dealsViewModel.mSelectedDeal.value?.title}")
    }
}