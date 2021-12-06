package tribore.onlinecinema.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import tribore.onlinecinema.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        val imgUri = url
            .toUri()
            .buildUpon()
            .scheme("https")
            .build()
        imageView.load(imgUri) {
            placeholder(R.drawable.ic_error)
            error(R.drawable.ic_broken_image)
        }
    }
}