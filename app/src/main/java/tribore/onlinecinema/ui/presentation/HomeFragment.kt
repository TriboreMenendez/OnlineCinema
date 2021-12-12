package tribore.onlinecinema.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tribore.onlinecinema.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tribore.onlinecinema.databinding.FragmentHomeBinding
import tribore.onlinecinema.ui.adapter.CinemaAdapter
import tribore.onlinecinema.ui.view_model.HomeViewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

    private var viewModelAdapter: CinemaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = homeViewModel
        viewModelAdapter = CinemaAdapter(CinemaClick())
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        homeViewModel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun goInfoCinema() {
        //Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_homeFragment_to_infoCinemaFragment)
    }

    private fun onNetworkError() {
        if (!homeViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            homeViewModel.onNetworkErrorShown()
        }
    }
}

class CinemaClick() {
    fun onClick() {

    }
}