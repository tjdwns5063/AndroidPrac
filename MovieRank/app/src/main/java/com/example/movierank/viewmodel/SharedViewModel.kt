package com.example.movierank.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierank.network.ImgApi
import com.example.movierank.network.InfoApi
import com.example.movierank.network.MovieInfo
import com.example.movierank.network.ResultData
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {
    private var movieData = MutableLiveData<List<ResultData>>()
    private var date: String? = null

    fun getMovieData(): LiveData<List<ResultData>> {
        loadData()
        return movieData
    }

    fun setDate(_date: String?) {
        date = _date
    }

    fun getDate() = date

    private fun loadData() {
        date ?: return
        viewModelScope.launch {
            Log.i("date", "viewModel Scope ${date}")
            try {
                val movie = InfoApi.infoApiService
                    .getInfo("c3c3c19c98650f46d506e0f334259831", date!!)
                    .movieInfos.dailyBoxOfficeList
                Log.i("date", "viewModel Scope2 ${movie[0].movieNm}")
                movieData.value = makeResultList(movie)
            } catch (e: Exception) {
                Log.i("date", "${e.message}")
            }
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