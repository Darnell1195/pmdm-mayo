package com.example.pmdm_mayo.client.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_mayo.client.domain.Client
import com.example.pmdm_mayo.R

class ClientsAdapter(
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {

    private var clients: List<Client> = emptyList()

    fun setClients(newClients: List<Client>) {
        clients = newClients
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = clients[position]
        holder.bind(client, onDeleteClick)
    }

    override fun getItemCount(): Int = clients.size

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
        }
    }
}