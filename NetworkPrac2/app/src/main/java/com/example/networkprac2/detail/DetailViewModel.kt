package com.example.networkprac2.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.networkprac2.network.DailyBoxOfficeList

class DetailViewModel(movieProperty: DailyBoxOfficeList, application: Application): AndroidViewModel(application) {
    private val _selectedProperty = MutableLiveData<DailyBoxOfficeList>()
    val selectedProperty: LiveData<DailyBoxOfficeList>
        get() = _selectedProperty

    init {
        _selectedProperty.value = movieProperty
    }
}