package com.sunitcb.gardenmate.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sunitcb.gardenmate.R
import com.sunitcb.gardenmate.entities.Plants
import org.w3c.dom.Text

class GardenLogAdapter(private val context: Context, private val onClick: (Plants) -> Unit) :
    RecyclerView.Adapter<GardenLogAdapter.GardenLogViewHolder>() {
    private var plantList = listOf<Plants>()

    class GardenLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gardenLogCardView: CardView = itemView.findViewById(R.id.gardenLogCardView)
        val plantAvatar: Button = itemView.findViewById(R.id.plantAvatar)
        val plantNameTxtView: TextView = itemView.findViewById(R.id.plantNameView)
        val plantDescriptionTxtView: TextView = itemView.findViewById(R.id.plantDescriptionView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenLogViewHolder {
        val gardenListView = LayoutInflater.from(parent.context).inflate(R.layout.garden_log_list, parent, false)
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
        holder.plantDescriptionTxtView.text = plantObj?.type
        holder.itemView.setOnClickListener {
            onClick(plantObj)
        }
    }

    fun setPlantList(plantList: List<Plants>) {
        this.plantList = plantList
        notifyDataSetChanged()
    }
}