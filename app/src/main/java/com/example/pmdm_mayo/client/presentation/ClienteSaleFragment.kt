package com.example.pmdm_mayo.client.presentation

import ClientsViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.pmdm_mayo.R
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ClienteSaleFragment : Fragment() {
    private val viewModel: ClientsViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val spinnerDni = view.findViewById<Spinner>(R.id.spinnerDni)
        val etConcept = view.findViewById<EditText>(R.id.etConcept)
        val etTotal = view.findViewById<EditText>(R.id.etTotal)
        val btnSave = view.findViewById<Button>(R.id.btnSaveSale)
        val btnBack = view.findViewById<Button>(R.id.btnBackSale)


        viewModel.clients.observe(viewLifecycleOwner) { clients ->
            val dnis = clients.map { it.dni }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dnis)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDni.adapter = adapter
        }


                btnSave.setOnClickListener {
                    val dni = spinnerDni.selectedItem?.toString() ?: ""
                    val concept = etConcept.text.toString()
                    val total = etTotal.text.toString()
                    if (dni != "" && concept != "" && total != "") {
                        Log.d("VentaDebug", "Guardando venta: dni=$dni, concepto=$concept, total=$total")
                        Toast.makeText(requireContext(), "Venta guardada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}