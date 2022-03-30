package com.example.recyclerprac

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.recyclerprac.database.MyModel

@BindingAdapter("setModelName")
fun TextView.setModelName(item: MyModel) {
    item?.let {
        text = "${item.nameId}\n${item.name}"
    }
}

@BindingAdapter("setImgRes")
fun ImageView.setImage(item: MyModel) {
    item?.let {
        if (item.nameId % 2 == 0) {
            setImageResource(R.drawable.gingerbread)
        } else {
            setImageResource(R.drawable.froyo)
        }
    }
}
