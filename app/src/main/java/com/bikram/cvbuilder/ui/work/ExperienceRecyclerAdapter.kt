package com.bikram.cvbuilder.ui.work

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bikram.cvbuilder.R
import com.bikram.cvbuilder.databinding.RecyclerViewExperienceItemBinding
import com.bikram.cvbuilder.models.Experience

class ExperienceRecyclerAdapter(private var experiences: List<Experience>, var context: Context) :
    RecyclerView.Adapter<ExperienceRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerViewExperienceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewExperienceItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(experiences[position]) {
                binding.jobTitleTextView.text = jobTitle
                binding.employerTextView.text = employer
                binding.durationTextView.text = "$startDate - $endDate"
                binding.locationTextView.text = "$address, $country"

                binding.responsibilityListView.adapter =
                    ArrayAdapter(
                        context,
                        R.layout.list_view_responsibility_item,
                        responsibilities
                    )
            }
        }
    }

    fun setItems(experiences: List<Experience>) {
        this.experiences = experiences
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return experiences.size
    }
}