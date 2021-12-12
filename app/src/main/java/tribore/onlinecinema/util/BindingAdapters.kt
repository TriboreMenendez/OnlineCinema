package tribore.onlinecinema.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tribore.onlinecinema.R
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.ui.adapter.CinemaAdapter

// Привязка загрузчика изображений
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

// Привязка ресайклера к нашей модели данных
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CinemaDomainModel>?) {
    val adapter = recyclerView.adapter as CinemaAdapter
    adapter.submitList(data)
}

// Привязка отображения ошибки при отсутствии интернета
@BindingAdapter("statusNetwork")
fun statusNetwork(view: View, status: Boolean) {
    when (status) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}

// Привязка отображения статуса загрузки данных
@BindingAdapter("statusLoad")
fun statusLoad(view: View, status: Boolean) {
    if (!status) view.visibility = View.GONE
}

