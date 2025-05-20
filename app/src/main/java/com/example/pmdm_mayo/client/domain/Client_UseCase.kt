package com.example.pmdm_mayo.client.domain

class GetClientsUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(): List<Client> = repository.getClients()
}
class DeleteClientUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(dni: String) = repository.deleteClient(dni)
}

