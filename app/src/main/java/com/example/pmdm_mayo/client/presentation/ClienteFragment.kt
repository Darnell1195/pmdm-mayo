package com.example.pmdm_mayo.client.presentation

import ClientsViewModel
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_mayo.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClienteFragment : Fragment(R.layout.fragment_client) {
    private val viewModel: ClientsViewModel by viewModel()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        val adapter = ClientsAdapter { dni: String ->
            viewModel.deleteClient(dni)
        }

        val rvClients = view.findViewById<RecyclerView>(R.id.rvClients)
        rvClients.layoutManager = LinearLayoutManager(requireContext())
        rvClients.adapter = adapter

        // Observa LiveData en vez de collectLatest
        viewModel.clients.observe(viewLifecycleOwner, Observer { clients ->
            adapter.submitList(clients)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMsg ->
            errorMsg?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        })
    }
}