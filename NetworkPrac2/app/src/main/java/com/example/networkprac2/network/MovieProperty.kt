package com.example.networkprac2.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class Result(
    val boxOfficeResult: BoxOfficeResult)

@JsonClass(generateAdapter = true)
data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val dailyBoxOfficeList: List<DailyBoxOfficeList>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class DailyBoxOfficeList(
    val rnum: String,
    val rank: String,
    val movieNm: String,
    val audiCnt: String,
    val audiAcc: String
): Parcelable