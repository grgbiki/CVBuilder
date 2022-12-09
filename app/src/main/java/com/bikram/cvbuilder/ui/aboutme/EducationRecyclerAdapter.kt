package com.bikram.cvbuilder.ui.aboutme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bikram.cvbuilder.databinding.RecyclerViewEducationItemBinding
import com.bikram.cvbuilder.models.Education

class EducationRecyclerAdapter(private var educations: List<Education>) :
    RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerViewEducationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewEducationItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(educations[position]) {
                binding.nameTextView.text = institution
                binding.degreeTextView.text = degree
            }
        }
    }

    fun setItems(educations: List<Education>) {
        this.educations = educations
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return educations.size
    }
}