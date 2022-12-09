package com.bikram.cvbuilder.ui.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bikram.cvbuilder.databinding.FragmentWorkBinding
import com.bikram.cvbuilder.ui.aboutme.EducationRecyclerAdapter
import com.bikram.cvbuilder.utils.SpacingItemDecorator

class WorkFragment : Fragment() {

    private var _binding: FragmentWorkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        val workViewModel =
            ViewModelProvider(this)[WorkViewModel::class.java]
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.experienceRecyclerView.isNestedScrollingEnabled = false
        binding.experienceRecyclerView.layoutManager = layoutManager

        val spacingItemDecorator = SpacingItemDecorator(50)
        binding.experienceRecyclerView.addItemDecoration(spacingItemDecorator)

        var adapter = ExperienceRecyclerAdapter(emptyList(), context!!)
        binding.experienceRecyclerView.adapter = adapter

        workViewModel.getExperience(context!!).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}