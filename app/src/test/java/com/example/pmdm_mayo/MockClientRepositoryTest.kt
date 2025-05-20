import com.example.pmdm_mayo.client.data.MockClientRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MockClientRepositoryTest {
    @Test
    fun testGetClientsReturnsInitialClients() = runBlocking {
        val repo = MockClientRepository()
        val clients = repo.getClients()
        assertTrue(clients.isNotEmpty())
    }
}