package kr.ac.konkuk.gdsc.gdscsuyeon.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import kr.ac.konkuk.gdsc.gdscsuyeon.R

@BindingAdapter("setDoneIcon")
fun setDoneIcon(view: ImageView, isDone: Boolean) {
    val imageRes = if (isDone) {
        R.drawable.icon
    } else {
        R.drawable.check
    }
    view.setImageResource(imageRes)
}