package com.target.dealbrowserpoc.dealbrowser.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DealsViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom((DealsViewModel::class.java))){
            return DealsViewModel() as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}