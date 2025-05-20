package com.example.pmdm_mayo.client.di

import ClientsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pmdm_mayo.client.domain.GetClientsUseCase
import com.example.pmdm_mayo.client.domain.DeleteClientUseCase
import com.example.pmdm_mayo.client.data.MockClientRepository
import com.example.pmdm_mayo.client.domain.AddClientUseCase
import com.example.pmdm_mayo.client.domain.ClientRepository

val appModule = module {
    viewModel { ClientsViewModel(get(), get(), get()) }
    factory { GetClientsUseCase(get()) }
    factory { DeleteClientUseCase(get()) }
    factory { AddClientUseCase(get()) }
    single<ClientRepository> { MockClientRepository() }
}