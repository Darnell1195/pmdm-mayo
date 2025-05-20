import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmdm_mayo.client.domain.DeleteClientUseCase
import com.example.pmdm_mayo.client.domain.GetClientsUseCase
import com.example.pmdm_mayo.client.domain.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientsViewModel(
    private val getClientsUseCase: GetClientsUseCase,
    private val deleteClientUseCase: DeleteClientUseCase
) : ViewModel() {

    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients: StateFlow<List<Client>> = _clients

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadClients()
    }

    fun deleteClient(dni: String) {
        viewModelScope.launch {
            try {
                deleteClientUseCase(dni)
                loadClients()
            } catch (e: Exception) {
                _error.value = "Error eliminando cliente"
            }
        }
    }

    private fun loadClients() {
        viewModelScope.launch {
            try {
                val result = getClientsUseCase()
                _clients.value = result
                _error.value = null
                if (result.isEmpty()) {
                    _error.value = "No hay clientes para mostrar"
                }
            } catch (e: Exception) {
                _error.value = "Error cargando clientes"
            }
        }
    }
}