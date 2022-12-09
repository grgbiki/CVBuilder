package com.bikram.cvbuilder.ui.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bikram.cvbuilder.databinding.FragmentAboutMeBinding
import com.bikram.cvbuilder.utils.SpacingItemDecorator


class AboutMeFragment : Fragment() {

    private var _binding: FragmentAboutMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        val aboutMeViewModel =
            ViewModelProvider(this)[AboutMeViewModel::class.java]
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.educationRecycleView.isNestedScrollingEnabled = false
        binding.educationRecycleView.layoutManager = layoutManager

        val spacingItemDecorator = SpacingItemDecorator(15)
        binding.educationRecycleView.addItemDecoration(spacingItemDecorator)

        var adapter = EducationRecyclerAdapter(emptyList())
        binding.educationRecycleView.adapter = adapter

        aboutMeViewModel.getEducation(context!!).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}