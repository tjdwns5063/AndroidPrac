package com.example.networkprac.overview

import android.graphics.Movie
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkprac.network.MovieApi
import com.example.networkprac.network.Boxoffice
import kotlinx.coroutines.launch

enum class MovieApiStatus { LOADING, ERROR, DONE }

class OverViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<List<Boxoffice>>()
    val property: LiveData<List<Boxoffice>>
        get() = _property

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<Boxoffice?>()
    val navigateToSelectedProperty: LiveData<Boxoffice?>
        get() = _navigateToSelectedProperty


    private var date: String = "20220312"

    init {
        getMarsRealEstateProperties()
    }

    fun dateIsClicked(view: EditText) {
        date = view.text.toString()
        getMarsRealEstateProperties()
        view.text.clear()
    }

    fun displayPropertyDetails(movieProperty: Boxoffice) {
        _navigateToSelectedProperty.value = movieProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun getMarsRealEstateProperties() {
        val myKey = "c3c3c19c98650f46d506e0f334259831"
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                _property.value = MovieApi.movieService.getProperties(myKey, date).boxOfficeResult.dailyBoxOfficeList
                _status.value = MovieApiStatus.DONE
                _response.value = "Success: ${property.value!!.size} movie properties retrieved"
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _property.value = ArrayList()
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}