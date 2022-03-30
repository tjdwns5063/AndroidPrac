package com.example.networkprac.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.networkprac.network.Boxoffice

class DetailViewModel(movieProperty: Boxoffice, application: Application): AndroidViewModel(application) {
    private val _selectedProperty = MutableLiveData<Boxoffice>()
    val selectedProperty: LiveData<Boxoffice>
        get() = _selectedProperty

    init {
        _selectedProperty.value = movieProperty
    }
}