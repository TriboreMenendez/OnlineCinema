package tribore.onlinecinema.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tribore.onlinecinema.databinding.CinemaItemBinding
import tribore.onlinecinema.domain.models.CinemaDomainModel

class CinemaAdapter(val clickListener: CinemaClick) :
    ListAdapter<CinemaDomainModel, CinemaAdapter.CinemaViewHolder>(DiffCallback) {

    class CinemaViewHolder(private var binding: CinemaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cinemaItem: CinemaDomainModel, clickListener: CinemaClick) {
            binding.also {
                it.cinema = cinemaItem
                it.clickListener = clickListener
                it.executePendingBindings()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CinemaDomainModel>() {
        override fun areItemsTheSame(
            oldItem: CinemaDomainModel,
            newItem: CinemaDomainModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CinemaDomainModel,
            newItem: CinemaDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CinemaViewHolder {
        return CinemaViewHolder(
            CinemaItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}


/*class CinemaClick(val click: (CinemaDomainModel) -> Unit) {
    fun onClick(cinema: CinemaDomainModel) = click(cinema)
}*/

class CinemaClick(val click: (CinemaDomainModel) -> Unit) {
    fun onClick(cinema: CinemaDomainModel) = click(cinema)
}
