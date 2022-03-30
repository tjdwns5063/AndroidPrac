package com.example.networkprac.network

import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Result(
    val boxOfficeResult: boxOfficeResult,
)

@JsonClass(generateAdapter = true)
data class boxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val dailyBoxOfficeList: List<Boxoffice>
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Boxoffice(
    val rnum: String,
    val rank: String,
    //val rankInten: String,
    //val rankOldAndNew: String,
    //val movieCd: String,
    val movieNm: String,
    val openDt: String,
    //val salesAmt: String,
    //val salesShare: String,
    //val salesInten: String,
    //val salesChange: String,
    //val salesAcc: String,
    val audiCnt: String,
    //val audiInten: String,
    //val audiChange: String,
    val audiAcc: String,
    //val scrnCnt: String,
    //val showCnt: String
): Parcelable