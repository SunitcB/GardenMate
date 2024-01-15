package com.sunitcb.gardenmate.ui.plantDetails

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sunitcb.gardenmate.MainActivity
import com.sunitcb.gardenmate.databinding.FragmentPlantDetailsBinding
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.ui.gardenLogs.GardenLogsFragment


class PlantDetailsFragment : Fragment() {

    private var _binding: FragmentPlantDetailsBinding? = null

    private lateinit var viewModel: PlantDetailsViewModel
    private var plantId: Long = 0L
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlantDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        plantId = arguments?.getLong("plantId") ?: 0L
        viewModel = ViewModelProvider(this).get(PlantDetailsViewModel::class.java)
        viewModel.getPlantById(plantId).observe(viewLifecycleOwner, Observer { plant ->
            plant?.let { displayPlantDetails(plant, binding) }
        })

        val activity = requireActivity() as MainActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.toggle.isDrawerIndicatorEnabled = false
        activity.toggle.setToolbarNavigationClickListener {
            activity.onBackPressed()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayPlantDetails(plant: Plants, binding: FragmentPlantDetailsBinding) {
        binding.plantNameTxtView.text = plant?.name
        binding.plantDescriptionTxtView.text = plant?.description
        binding.plantTypeTxtView.text = plant?.type
        binding.wateringFrequencyTxtView.text = plant?.wateringFrequency.toString()
        binding.plantedDateTxtView.text = plant?.plantedDate
    }
}