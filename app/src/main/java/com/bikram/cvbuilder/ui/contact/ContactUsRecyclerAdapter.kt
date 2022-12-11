package com.bikram.cvbuilder.ui.contact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bikram.cvbuilder.databinding.RecyclerViewItemWithSubtextBinding
import com.bikram.cvbuilder.models.Contact
import com.bikram.cvbuilder.ui.webview.WebViewActivity
import com.bikram.cvbuilder.utils.LoadDrawableImage


class ContactUsRecyclerAdapter(
    private val context: Context,
    private var contacts: List<Contact>
) :
    RecyclerView.Adapter<ContactUsRecyclerAdapter.ViewHolder>() {
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
            with(contacts[position]) {
                binding.titleTextView.text = value
                binding.subTitleTextView.text = type
                binding.logo.setImageResource(LoadDrawableImage.getDrawableImage(context, logo))
                binding.root.setOnClickListener {
                    var intent: Intent? = null
                    when (type) {
                        "Mobile" -> {
                            intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:$value")
                            context.startActivity(intent)
                        }
                        "Email" -> {
                            intent = Intent(
                                Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", value, null
                                )
                            )
                            context.startActivity(Intent.createChooser(intent, null))
                        }
                        "LinkedIn Website", "GitHub" -> {
                            intent = Intent(context, WebViewActivity::class.java)
                            intent.putExtra("url", value)
                        }
                        "Resume pdf" -> {}
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    fun setItems(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}