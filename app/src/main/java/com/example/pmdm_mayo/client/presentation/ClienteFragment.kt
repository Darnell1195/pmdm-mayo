package com.example.pmdm_mayo.client.presentation

import ClientsViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_mayo.R
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ClienteFragment : Fragment(R.layout.fragment_client) {
    private val viewModel: ClientsViewModel by activityViewModel()

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

        viewModel.clients.observe(viewLifecycleOwner, Observer { clients ->
            adapter.setClients(clients)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMsg ->
            errorMsg?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        })

        val menuHost = requireActivity() as androidx.core.view.MenuHost
        menuHost.addMenuProvider(object : androidx.core.view.MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_client, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_add -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, AddClientFragment())
                            .addToBackStack(null)
                            .commit()
                        true
                    }
                    R.id.action_add_sale -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ClienteSaleFragment())
                            .addToBackStack(null)
                            .commit()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)
    }
}