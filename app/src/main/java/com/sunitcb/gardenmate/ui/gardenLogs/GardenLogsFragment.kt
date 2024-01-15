package com.sunitcb.gardenmate.ui.gardenLogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sunitcb.gardenmate.MainActivity
import com.sunitcb.gardenmate.R
import com.sunitcb.gardenmate.databinding.FragmentGardenLogsBinding
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.ui.adapter.GardenLogAdapter

class GardenLogsFragment : Fragment() {

    private var _binding: FragmentGardenLogsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: GardenLogsViewModel
    private lateinit var gardenLogAdapter: GardenLogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGardenLogsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(GardenLogsViewModel::class.java)

        var recyclerView = binding.recipeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        gardenLogAdapter = GardenLogAdapter(this.requireContext()) { plant ->
            val itemAction = plant?.id?.let {
                GardenLogsFragmentDirections.actionNavGardenLogsToNavPlantDetails(
                    it
                )
            }
            if (itemAction != null) {
                findNavController().navigate(itemAction)
            }
        }

        recyclerView.adapter = gardenLogAdapter

        binding.addNewGardenLog.setOnClickListener {
            val addPlantAction = GardenLogsFragmentDirections.actionNavGardenLogsToNavAddPlant()
            findNavController().navigate(addPlantAction)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allPlants.observe(viewLifecycleOwner) { plants ->
            gardenLogAdapter.setPlantList(plants)
        }
        val activity = requireActivity() as MainActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.toggle.isDrawerIndicatorEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}