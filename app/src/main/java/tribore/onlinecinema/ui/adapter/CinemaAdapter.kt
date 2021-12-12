package tribore.onlinecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tribore.onlinecinema.R
import tribore.onlinecinema.databinding.CinemaItemBinding
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.ui.presentation.CinemaClick

class CinemaAdapter (val callback: CinemaClick) : RecyclerView.Adapter<CinemaViewHolder>() {

    var videos: List<CinemaDomainModel> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val withDataBinding: CinemaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CinemaViewHolder.LAYOUT,
            parent,
            false)
        return CinemaViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.cinema = videos[position]
            it.videoCallback = callback
        }
    }

}

/**
 * ViewHolder for DevByte items. All work is done by data binding.
 */
class CinemaViewHolder(val viewDataBinding: CinemaItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.cinema_item
    }
}