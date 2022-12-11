package com.bikram.cvbuilder.ui.aboutme

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bikram.cvbuilder.databinding.RecyclerViewItemWithSubtextBinding
import com.bikram.cvbuilder.models.Education
import com.bikram.cvbuilder.utils.LoadDrawableImage

class EducationRecyclerAdapter(
    private val context: Context,
    private var educations: List<Education>
) :
    RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: RecyclerViewItemWithSubtextBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewItemWithSubtextBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(educations[position]) {
                binding.titleTextView.text = institution
                binding.subTitleTextView.text = degree
                binding.logo.setImageResource(LoadDrawableImage.getDrawableImage(context, logo))
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