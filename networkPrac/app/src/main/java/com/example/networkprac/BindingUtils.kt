package com.example.networkprac

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.networkprac.network.Boxoffice
import com.example.networkprac.overview.MovieAdapter
import com.example.networkprac.overview.MovieApiStatus

@BindingAdapter("list_data")
fun bindRecyclerView(view: RecyclerView, data: List<Boxoffice>?) {
    val adapter = view.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("img_view")
fun bindImg(view: ImageView, item: Boxoffice?) {
    val imgList = listOf(
        "https://image.news1.kr/system/hp/2022/2/10/5209973/dims/optimize",
        "https://t1.daumcdn.net/movie/8cc69ee37e41d9fed49935446d3a6697f7467df4",
        "https://img9.yna.co.kr/etc/inner/KR/2022/02/11/AKR20220211161600005_01_i_P2.jpg",
    "https://w.namu.la/s/51662e088324c7e0b5cfde321c768072fba9480247cf910fa3e0cb0e3bccdb05ec3f164e80fba5e5b0754ad5f2fa51f3e0e156e1c0ec5218a780b4b06bd85a82d5849752cbf62d1cbdef1c31e3b392242ee7e9cd64108c8b2186cadbdf541e6a",
    "https://w.namu.la/s/660278957e62d1b6d5d39a24b2acc9aa2549dbe3b9faf5266298746ca69227435331aa2d4de94b78203f8d4da419c161692bb21e01feef3778d825732a4ca48586c5a33c41bf04a3eb4906b4990acce0",
    "https://t1.daumcdn.net/movie/7bbd91ede7bdcd4d0073c1159e3c3dfb8a6ff048",
    "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/WNR2FAMSSFXPTJPVFHFWNUKKPA.jpg",
    "https://img.wkorea.com/w/2021/12/style_61b6deb58348e.jpg",
    "http://vision21.kr/data/photos/portnews/202202/20220222115205-6331.jpg",
    "https://dvdprime.com/g2/data/cheditor5/2112/view_thumbnail/mania-done-20211202202331_dytfuhzf.jpg")
    item?.let {
        val imgUri = imgList[item.rnum.toInt() - 1].toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(view)
    }
}

@BindingAdapter("movie_name_text")
fun bindMovieName(view: TextView, item: Boxoffice?) {
    item?.let {
        view.text = it.movieNm
    }
}

@BindingAdapter("movie_rank_text")
fun bindMovieRank(view: TextView, item: Boxoffice?) {
    item?.let {
        view.text = it.rank
    }
}

@BindingAdapter("status_img")
fun bindStatus(view: ImageView, status: MovieApiStatus) {
    when (status) {
        MovieApiStatus.LOADING -> {
            view.visibility = ImageView.VISIBLE
            view.setImageResource(R.drawable.loading_animation)
        }
        MovieApiStatus.ERROR -> {
            view.visibility = ImageView.VISIBLE
            view.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            view.visibility = ImageView.GONE
        }
    }
}

@BindingAdapter("today_cnt")
fun bindMovieAudiCnt(view: TextView, item: Boxoffice?) {
    item?.let {
        view.text = "오늘의 관객 수: ${it.audiCnt} 명"
    }
}

@BindingAdapter("all_cnt")
fun bindMovieAllAudiCnt(view: TextView, item: Boxoffice?) {
    item?.let {
        view.text = "총 누적 관객 수: ${it.audiAcc} 명"
    }
}