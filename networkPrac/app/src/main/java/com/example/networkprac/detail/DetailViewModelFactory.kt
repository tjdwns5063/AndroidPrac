package com.example.networkprac.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkprac.network.Boxoffice
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val movieProperty: Boxoffice,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
