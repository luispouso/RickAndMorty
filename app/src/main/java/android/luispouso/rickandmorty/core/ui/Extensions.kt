package android.luispouso.rickandmorty.core.ui

import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun Fragment.showToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImageWithGlide(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .circleCrop()
        .into(this)
}