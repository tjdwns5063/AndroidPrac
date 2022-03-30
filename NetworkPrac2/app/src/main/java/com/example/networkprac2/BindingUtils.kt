package com.example.networkprac2

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.networkprac2.network.DailyBoxOfficeList
import com.example.networkprac2.overview.MovieAdapter

@BindingAdapter("list_item")
fun bindRecyclerView(view: RecyclerView, item: List<DailyBoxOfficeList>?) {
    item?.let {
        val adapter = view.adapter as MovieAdapter
        adapter.submitList(item)
    }
}

@BindingAdapter("movie_name")
fun bindMovieName(view: TextView, item: DailyBoxOfficeList?) {
    item?.let {
        view.text = item.movieNm
    }
}

@BindingAdapter("movie_rank")
fun bindMovieRank(view: TextView, item: DailyBoxOfficeList?) {
    item?.let {
        view.text = item.rank
    }
}

@BindingAdapter("movie_img")
fun bindMovieImage(view: ImageView, item: DailyBoxOfficeList?) {
    val movieImages = mapOf<String, String>(
        "이상한 나라의 수학자" to "https://image.news1.kr/system/hp/2022/2/10/5209973/dims/optimize",
        "더 배트맨" to "https://w.namu.la/s/61ba6c0640c820f7246aa5ac7ed7f2e110c55b07225a7944a6b7d1b6d747a6ef5b04729b31221110974fe75a431dde15fa32cca927536c67a014b3b57082c00b5a08f0771f77d552bb2d3d63287281bd535a37d268d64af3c28831e7ae90e856",
        "극장판 주술회전 0" to "https://img9.yna.co.kr/etc/inner/KR/2022/02/11/AKR20220211161600005_01_i_P2.jpg",
        "블랙라이트" to "https://t1.daumcdn.net/movie/7bbd91ede7bdcd4d0073c1159e3c3dfb8a6ff048",
        "해적: 도깨비 깃발" to "https://w.namu.la/s/09bcfe59fdf822daef2c6e7f2c6d38f9f2d94e8e49d585c3a888480c9bca7997662524e6e109daffb1dc136041eb98f485c5b720c10a723c3c3dcf7dfd609fd94b290053f96ee1ae79b24295c2398a3a986f1afab4c7368ab53e3ff88634793f",
        "나의 히어로 아카데미아 더 무비: 월드 히어로즈 미션" to "http://t1.daumcdn.net/movie/7d28daed3b184cd58415a57c038343d01576843784335",
        "언차티드" to "https://file.mk.co.kr/meet/neds/2022/02/image_readtop_2022_171596_16455781304955116.jfif",
        "레벤느망" to "http://cdn.slist.kr/news/photo/202202/333444_550206_1145.jpeg",
        "스파이더맨: 노 웨이 홈" to "https://file.mk.co.kr/meet/neds/2021/12/image_readtop_2021_1111761_16384267724870468.jpg",
        "인민을 위해 복무하라" to "https://images.chosun.com/resizer/Vs843nFsaKmVmO_nhSGd170GfkY=/530x757/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/DQLFKKTCHXX2GKZCWE2D2WAG2M.jpg",
        "안테벨룸" to "https://cdn.mhns.co.kr/news/photo/202201/519563_628880_397.jpg",
        "나이트메어 앨리" to "https://dvdprime.com/g2/data/cheditor5/2112/view_thumbnail/mania-done-20211202202331_dytfuhzf.jpg"
    )

    item?.let {
        val uri = movieImages.get(it.movieNm)!!.toUri().buildUpon().scheme("https").build()

        Glide.with(view.context)
            .load(uri)
            .into(view)
    }
}

@BindingAdapter("detail_movie_name")
fun bindDetailMovieName(view: TextView, item: DailyBoxOfficeList?) {
    item?.let {
        view.text = "제목 : ${item.movieNm}"
    }
}

@BindingAdapter("movie_audi_cnt")
fun bindMovieAudiCnt(view: TextView, item: DailyBoxOfficeList?) {
    item?.let {
        view.text = "이날의 관객 수 : ${item.audiCnt}"
    }
}

@BindingAdapter("movie_audi_acc")
fun bindMovieAudiAcc(view: TextView, item: DailyBoxOfficeList?) {
    item?.let {
        view.text = "누적 관객 수 : ${item.audiAcc}"
    }
}