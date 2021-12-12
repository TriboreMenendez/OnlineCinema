package tribore.onlinecinema.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tribore.onlinecinema.R
import tribore.onlinecinema.databinding.FragmentHomeBinding
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.ui.adapter.CinemaAdapter
import tribore.onlinecinema.ui.adapter.CinemaClick
import tribore.onlinecinema.ui.view_model.HomeViewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = homeViewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.recyclerView.adapter = CinemaAdapter(CinemaClick {
            cinemaModel -> goInfoCinema(cinemaModel)
        })

        return binding.root
    }


    private fun goInfoCinema(cinemaItem: CinemaDomainModel) {
        findNavController().navigate(R.id.action_homeFragment_to_infoCinemaFragment)
    }
}