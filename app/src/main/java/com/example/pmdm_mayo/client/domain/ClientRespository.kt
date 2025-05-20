package com.example.pmdm_mayo.client.domain

interface ClientRepository {
    suspend fun getClients(): List<Client>
    suspend fun deleteClient(dni: String)
}