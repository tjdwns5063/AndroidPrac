package com.example.viewmodelprac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {
    var _currentNumb = MutableLiveData<Int>()
    val currentNumb: LiveData<Int>
        get() = _currentNumb
    init {
        _currentNumb.value = 0
    }
    fun onUp() {
        _currentNumb.value = (_currentNumb.value)?.plus(1)
    }
}