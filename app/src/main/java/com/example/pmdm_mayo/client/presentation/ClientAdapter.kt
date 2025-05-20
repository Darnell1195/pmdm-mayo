package com.example.pmdm_mayo.client.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_mayo.client.domain.Client
import com.example.pmdm_mayo.R

class ClientsAdapter(
    private val onDeleteClick: (String) -> Unit
) : ListAdapter<Client, ClientsAdapter.ViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = getItem(position)
        holder.bind(client, onDeleteClick)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDni: TextView = itemView.findViewById(R.id.tvDni)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(client: Client, onDeleteClick: (String) -> Unit) {
            tvDni.text = client.dni
            tvName.text = client.name
            tvEmail.text = client.email
            btnDelete.setOnClickListener { onDeleteClick(client.dni) }
            itemView.setOnClickListener(null) // Elimina el listener del itemView
        }
    }
}

class ClientDiffCallback : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean =
        oldItem.dni == newItem.dni

    override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean =
        oldItem == newItem
}