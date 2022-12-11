package com.bikram.cvbuilder.ui.contact

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bikram.cvbuilder.databinding.FragmentAboutMeBinding
import com.bikram.cvbuilder.databinding.FragmentContactBinding
import com.bikram.cvbuilder.ui.aboutme.AboutMeViewModel
import com.bikram.cvbuilder.ui.aboutme.EducationRecyclerAdapter
import com.bikram.cvbuilder.utils.SpacingItemDecorator

class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.contactRecyclerView.isNestedScrollingEnabled = false
        binding.contactRecyclerView.layoutManager = layoutManager

        val spacingItemDecorator = SpacingItemDecorator(25)
        binding.contactRecyclerView.addItemDecoration(spacingItemDecorator)

        val adapter =
            ContactUsRecyclerAdapter(context!!, emptyList())
        binding.contactRecyclerView.adapter = adapter

        contactViewModel.getContact(context!!).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}