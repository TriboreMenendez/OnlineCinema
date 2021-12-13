package tribore.onlinecinema.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import tribore.onlinecinema.R
import tribore.onlinecinema.databinding.FragmentInfoCinemaBinding
import tribore.onlinecinema.ui.view_model.HomeViewModel

class InfoCinemaFragment : Fragment() {
    private val homeViewModel: HomeViewModel by inject()

    lateinit var binding: FragmentInfoCinemaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_cinema, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_infoCinemaFragment_to_homeFragment)
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_infoCinemaFragment_to_playerFragment)
        }
    }

}