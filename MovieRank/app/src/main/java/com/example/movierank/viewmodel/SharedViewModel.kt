package com.example.movierank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierank.network.ImgApi
import com.example.movierank.network.InfoApi
import com.example.movierank.network.MovieInfo
import com.example.movierank.network.ResultData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {
    private val movieData: MutableLiveData<List<ResultData>> by lazy {
        MutableLiveData<List<ResultData>>().also { loadData() }
    }

    fun getMovieData(): LiveData<List<ResultData>> {
        return movieData
    }

    private fun loadData() {
        viewModelScope.launch {
            val movie = InfoApi.infoApiService
                .getInfo("c3c3c19c98650f46d506e0f334259831", "20220515")
                .movieInfos.dailyBoxOfficeList
            movieData.value = makeResultList(movie)
        }
    }

    private suspend fun makeResultList(movie: List<MovieInfo>): List<ResultData> {
        val res = mutableListOf<ResultData>()

        for (element in movie) {
            val img = ImgApi.imgApiService
                .getInfo("wXBGXN37UJXeKYZbR7YF", "CEbyccy9YN", element.movieNm)
                .items[0].image
            val resultData = ResultData(element, img)
            res.add(resultData)
        }
        return (res)
    }
}