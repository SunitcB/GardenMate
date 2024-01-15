package com.sunitcb.gardenmate.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sunitcb.gardenmate.R
import com.sunitcb.gardenmate.entities.Plants
import org.w3c.dom.Text

class GardenLogAdapter(private val context: Context, private val onClick: (Plants) -> Unit, private val deletePlant: (Plants) -> Unit) :
    RecyclerView.Adapter<GardenLogAdapter.GardenLogViewHolder>() {
    private var plantList = listOf<Plants>()

    class GardenLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gardenLogCardView: CardView = itemView.findViewById(R.id.gardenLogCardView)
        val plantAvatar: Button = itemView.findViewById(R.id.plantAvatar)
        val plantNameTxtView: TextView = itemView.findViewById(R.id.plantNameView)
        val plantTypeTxtView: TextView = itemView.findViewById(R.id.plantTypeView)
        val plantDescriptionTxtView: TextView = itemView.findViewById(R.id.plantDescriptionView)
        val plantDeleteBtn: Button = itemView.findViewById(R.id.deletePlantBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenLogViewHolder {
        val gardenListView =
            LayoutInflater.from(parent.context).inflate(R.layout.garden_log_list, parent, false)
        return GardenLogViewHolder(gardenListView)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: GardenLogViewHolder, position: Int) {
        val plantObj = plantList[position]
        val foodInitials = plantObj?.name.toString()
            .split(' ')
            .take(2)
            .mapNotNull { it.firstOrNull()?.toString() }
            .reduce { acc, s -> acc + s }

        holder.plantAvatar.text = foodInitials
        holder.plantNameTxtView.text = plantObj?.name
        holder.plantDescriptionTxtView.text = plantObj?.description
        holder.plantTypeTxtView.text = plantObj?.type
        holder.itemView.setOnClickListener {
            onClick(plantObj)
        }

        holder.plantDeleteBtn.setOnClickListener {
            deletePlant(plantObj)
        }
    }

    fun setPlantList(plantList: List<Plants>) {
        this.plantList = plantList
        notifyDataSetChanged()
    }
}