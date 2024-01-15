package com.sunitcb.gardenmate.ui.addPlant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunitcb.gardenmate.MainActivity
import com.sunitcb.gardenmate.R
import com.sunitcb.gardenmate.databinding.FragmentAddPlantBinding
import com.sunitcb.gardenmate.databinding.FragmentPlantDetailsBinding
import com.sunitcb.gardenmate.entities.Plants
import com.sunitcb.gardenmate.ui.gardenLogs.GardenLogsFragmentDirections
import com.sunitcb.gardenmate.ui.plantDetails.PlantDetailsViewModel
import kotlin.math.log

class AddPlantFragment : Fragment() {

    companion object {
        fun newInstance() = AddPlantFragment()
    }

    private var _binding: FragmentAddPlantBinding? = null

    private var plantId: Long = 0L
    private val binding get() = _binding!!
    private lateinit var viewModel: AddPlantViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val activity = requireActivity() as MainActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)
        activity.toggle.isDrawerIndicatorEnabled = false
        activity.toggle.setToolbarNavigationClickListener {
            activity.onBackPressed()
        }

        var plantNameField: EditText = binding.plantNameField
        var plantDescriptionField: EditText = binding.plantDescriptionField
        var plantTypeField: EditText = binding.plantTypeField
        var wateringFrequencyField: EditText = binding.wateringFrequency
        var addNewPlantBtn: Button = binding.addPlantBtn

        addNewPlantBtn.setOnClickListener {
            if (addNewPlant(
                    plantNameField.text.toString(),
                    plantDescriptionField.text.toString(),
                    plantTypeField.text.toString(),
                    wateringFrequencyField.text.toString().toInt()
                )
            ) {
                Snackbar.make(
                    requireView(),
                    R.string.notification_add_plant_success,
                    Snackbar.LENGTH_SHORT
                ).show()
                val addPlantAction = AddPlantFragmentDirections.actionNavAddPlantToNavGardenLogs()
                findNavController().navigate(addPlantAction)
            }
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddPlantViewModel::class.java)
    }

    fun addNewPlant(
        plantName: String,
        plantDescription: String,
        plantType: String,
        wateringFrequency: Int
    ): Boolean {
        var isSuccess: Boolean = false;
        try {
            viewModel.insert(Plants(0, plantName, plantDescription, plantType, wateringFrequency))
            isSuccess = true
        } catch (ex: Exception) {
            println("Exception in adding new plant: " + ex.stackTrace)
        }

        return isSuccess
    }

}