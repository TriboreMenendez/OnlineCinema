package tribore.onlinecinema.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import tribore.onlinecinema.R
import tribore.onlinecinema.databinding.CinemaItemBinding
import tribore.onlinecinema.domain.models.CinemaDomainModel

class CinemaListAdapter(
    private var cinemaList: List<CinemaDomainModel>,
) : RecyclerView.Adapter<CinemaListAdapter.CinemaViewHolder>() {

    class CinemaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView

        init {
            this.title = view.findViewById(R.id.titleText)

        }
        /*fun bind(cinema: CinemaDomainModel) {
            binding.titleText.text = cinema.title
            binding.genreText.text = cinema.genres[0].name
            binding.relizeDateText.text = cinema.releaseDate
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cinema_item, parent, false)
        return CinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val cinema = cinemaList[position]
        holder.title.text = cinemaList[position].title
    }

    override fun getItemCount() = cinemaList.size

    fun updateRecylerView(updatedRecycler: List<CinemaDomainModel>) {
        cinemaList = updatedRecycler
        notifyDataSetChanged()
    }
}