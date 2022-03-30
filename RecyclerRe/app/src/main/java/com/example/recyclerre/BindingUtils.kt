package com.example.recyclerre

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.recyclerre.database.MyModel

@BindingAdapter("set_text")
fun TextView.setText(model: MyModel) {
    text = model.name
}

@BindingAdapter("set_img")
fun ImageView.setImg(model: MyModel) {
    if (model.nameId % 2 == 0) {
        setImageResource(R.drawable.froyo)
    } else {
        setImageResource(R.drawable.gingerbread)
    }
}