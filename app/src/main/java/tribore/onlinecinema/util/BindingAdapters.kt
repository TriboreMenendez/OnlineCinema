package tribore.onlinecinema.util

import android.view.View
import androidx.databinding.BindingAdapter



@BindingAdapter("isNetworkError", "cinema")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}

/*@BindingAdapter("imageUrl")
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

// Используется для привязки макета xml с данными
@BindingAdapter("listCinema")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CinemaDomainModel>?) {
    val adapter = recyclerView.adapter as CinemaListAdapter
    adapter.submitList(data)
}*/

