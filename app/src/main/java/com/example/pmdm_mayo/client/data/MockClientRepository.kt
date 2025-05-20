package com.example.pmdm_mayo.client.data

import com.example.pmdm_mayo.client.domain.Client
import com.example.pmdm_mayo.client.domain.ClientRepository

class MockClientRepository : ClientRepository {
    private val clients = mutableListOf(
        Client("12345678A", "Ana López", "ana@example.com"),
        Client("87654321B", "Carlos Ruiz", "carlos@example.com"),
        Client("11223344C", "María García", "maria@example.com"),
        Client("22334455D", "Juan Pérez", "juan@example.com"),
        Client("33445566E", "Lucía Fernández", "lucia@example.com"),
        Client("44556677F", "Pedro Martínez", "pedro@example.com"),
        Client("55667788G", "Sofía Sánchez", "sofia@example.com")

    )

    override suspend fun getClients(): List<Client> = clients.toList()
    override suspend fun deleteClient(dni: String) {
        clients.removeIf { it.dni == dni }
    }
}