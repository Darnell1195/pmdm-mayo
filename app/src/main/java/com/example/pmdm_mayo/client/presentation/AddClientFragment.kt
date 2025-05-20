package com.example.pmdm_mayo.client.presentation

import ClientsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pmdm_mayo.R
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddClientFragment : Fragment() {
    private val viewModel: ClientsViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_client, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etDni = view.findViewById<EditText>(R.id.etDni)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnSave.setOnClickListener {
            val dni = etDni.text.toString()
            val name = etName.text.toString()
            val email = etEmail.text.toString()

        if (dni != "" && name != "" && email != "") {
           viewModel.addClient(dni, name, email)
           Toast.makeText(requireContext(), "Cliente añadido", Toast.LENGTH_SHORT).show()
       } else {
           Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
       }
        }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}