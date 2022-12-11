package com.bikram.cvbuilder.ui.aboutme

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bikram.cvbuilder.R
import com.bikram.cvbuilder.databinding.FragmentAboutMeBinding
import com.bikram.cvbuilder.utils.SpacingItemDecorator
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AboutMeFragment : Fragment() {

    private var _binding: FragmentAboutMeBinding? = null
    private var aboutMeViewModel: AboutMeViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val strengths: MutableList<String> = ArrayList()
    private val weakness: MutableList<String> = ArrayList()
    private var strengthAdapter: ArrayAdapter<String>? = null
    private var weaknessAdapter: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        aboutMeViewModel = ViewModelProvider(this)[AboutMeViewModel::class.java]
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.educationRecycleView.isNestedScrollingEnabled = false
        binding.educationRecycleView.layoutManager = layoutManager

        val spacingItemDecorator = SpacingItemDecorator(15)
        binding.educationRecycleView.addItemDecoration(spacingItemDecorator)

        var adapter = EducationRecyclerAdapter(context!!, emptyList())
        binding.educationRecycleView.adapter = adapter

        aboutMeViewModel!!.getEducation(context!!).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        strengthAdapter = ArrayAdapter( context!!, R.layout.list_view_item, strengths)
        binding.strengthListView.adapter = strengthAdapter
        aboutMeViewModel!!.getStrengths(context!!).observe(viewLifecycleOwner) {
            strengths.clear()
            it.forEach { strength ->
                strengths.add(strength)
            }
            strengthAdapter!!.notifyDataSetChanged()
        }

        weaknessAdapter = ArrayAdapter( context!!, R.layout.list_view_item, weakness)
        binding.weaknessListView.adapter = weaknessAdapter
        aboutMeViewModel!!.getWeakness(context!!).observe(viewLifecycleOwner) {
            weakness.clear()
            it.forEach { strength ->
                weakness.add(strength)
            }
            strengthAdapter!!.notifyDataSetChanged()
        }

        binding.fabAddMore.setOnClickListener {
            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog)

            val cancelBtn: MaterialButton = dialog.findViewById(R.id.cancel_btn)
            val addBtn: MaterialButton = dialog.findViewById(R.id.add_btn)
            val spinner: Spinner = dialog.findViewById(R.id.option_spinner)
            val editText: TextInputEditText = dialog.findViewById(R.id.strength_weakness_edit_text)
            val editTextLayout: TextInputLayout =
                dialog.findViewById(R.id.strength_weakness_edit_text_layout)

            val options: List<String> = listOf("Strength", "Weakness")
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, options).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    editTextLayout.hint = "Your ${options[position]}"
                }
            }
            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }
            addBtn.setOnClickListener {
                if (validateTextField(editText, editTextLayout)) {
                    val userInput = editText.text.toString().trim()
                    when (spinner.selectedItem) {
                        "Strength" -> {
                            strengths.add(userInput)
                            aboutMeViewModel!!.addStrengths(context!!, userInput)
                        }
                        "Weakness" -> {
                            weakness.add(userInput)
                            aboutMeViewModel!!.addWeakness(context!!, userInput)
                        }
                    }
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun validateTextField(textField: TextInputEditText, textFieldLayout: TextInputLayout): Boolean {
        if (textField.text.toString().trim().isEmpty()) {
            textFieldLayout.isErrorEnabled = true
            textFieldLayout.error = "Required Field!"
            textField.requestFocus()
            return false
        } else {
            textFieldLayout.isErrorEnabled = false
        }
        return true
    }
}