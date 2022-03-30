package com.example.networkprac2.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkprac2.network.DailyBoxOfficeList
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val movieProperty: DailyBoxOfficeList,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieProperty, application) as T
        }
        throw IllegalArgumentException("unknown viewmodel type")
    }
}