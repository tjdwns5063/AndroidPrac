package com.example.movierank.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MovieResult(
    @Json(name="boxOfficeResult")
    val movieInfos: MovieInfos
)

//kobis api
@JsonClass(generateAdapter = true)
data class MovieInfos(
    val boxofficeType: String,
    val showRange: String,
    val dailyBoxOfficeList: List<MovieInfo>
)

@JsonClass(generateAdapter = true)
data class MovieInfo(
    val rnum: String,
    val rank: String,
    val movieNm: String,
    val openDt: String,
    val audiAcc: String
)
// naver movie api
data class MovieImg(
    val items: List<Img>
)

@JsonClass(generateAdapter = true)
data class Img(
    val image: String
)

// result data
data class ResultData(
    val movieInfo: MovieInfo,
    val image: String
)