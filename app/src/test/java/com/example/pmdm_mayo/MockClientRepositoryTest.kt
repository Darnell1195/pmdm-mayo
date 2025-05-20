import com.example.pmdm_mayo.client.data.MockClientRepository
import com.example.pmdm_mayo.client.domain.Client
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MockClientRepositoryTest {
    @Test
    fun testAddAndGetClients() = runBlocking {
        val repo = MockClientRepository()
        val initialSize = repo.getClients().size
        repo.addClient(Client("99999999Z", "Test", "test@example.com"))
        val clients = repo.getClients()
        assertEquals(initialSize + 1, clients.size)
        assertTrue(clients.any { it.dni == "99999999Z" })
    }
}