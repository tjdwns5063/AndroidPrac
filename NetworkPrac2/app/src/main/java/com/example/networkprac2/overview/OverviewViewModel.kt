package com.example.networkprac2.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkprac2.network.DailyBoxOfficeList
import com.example.networkprac2.network.MovieApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class OverviewViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<List<DailyBoxOfficeList>>()
    val property: LiveData<List<DailyBoxOfficeList>>
        get() = _property

    private val _navigateToSelectedProperty = MutableLiveData<DailyBoxOfficeList?>()
    val navigateToSelectedProperty: LiveData<DailyBoxOfficeList?>
        get() = _navigateToSelectedProperty

    private var date: String

    init {
        date = ""
    }

    private fun getMovieProperties(date: String) {
        val key = "c3c3c19c98650f46d506e0f334259831"
        viewModelScope.launch {
            try {
                _property.value = MovieApi.movieService.getProperties(key, date).boxOfficeResult.dailyBoxOfficeList
                _response.value = "data accept ${property.value!!.size}."
                Log.i("Result", "try ${_response.value}")
            } catch (e: Exception) {
                Log.i("Result", "catch ${e.message}")
                _response.value = "Failure " + e.message
            }
        }
    }

    fun displayDetailProperty(movieProperty: DailyBoxOfficeList) {
        _navigateToSelectedProperty.value = movieProperty
    }

    fun displayDetailComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun onYesterdayClick() {
        date = "20220310"
        getMovieProperties(date)
    }

    fun onTodayClick() {
        date = "20220311"
        getMovieProperties(date)
    }

    fun onTomorrowClick() {
        date = "20220312"
        getMovieProperties(date)
    }
}