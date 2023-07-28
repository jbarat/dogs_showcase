package hu.jozsefbarat.domain.breed

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllBreedsUseCaseTest {

    private lateinit var useCase: GetAllBreedsUseCase

    private val breedsGatewayMock = mockk<BreedsGateway>()

    @Before
    fun setup() {
        useCase = GetAllBreedsUseCase(breedsGatewayMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when empty value received then return it`() {
        coEvery { breedsGatewayMock.getAllDogBreeds() } returns emptyList()

        runBlocking {
            val result = useCase.execute()

            assert(result == emptyList<Breed>())
        }
    }

    @Test
    fun `when exception received then return it`() {
        coEvery { breedsGatewayMock.getAllDogBreeds() } throws Exception("mocked exception")

        runBlocking {
            try {
                useCase.execute()

                Assert.fail("Should throw exception")
            } catch (e: Exception) {
                assert(e.message == "mocked exception")
            }
        }
    }

    @Test
    fun `when correct values received then return them sorted`() {
        coEvery { breedsGatewayMock.getAllDogBreeds() } returns unsortedResponse

        runBlocking {
            val result = useCase.execute()

            assert(result == sortedResponse)
        }
    }
}

private val unsortedResponse = listOf(
    Breed(
        "b", listOf(
            Breed("b1", emptyList()),
            Breed("b2", emptyList()),
        )
    ),
    Breed("a", emptyList()),
    Breed("c", emptyList()),
)

private val sortedResponse = listOf(
    Breed("a", emptyList()),
    Breed(
        "b", listOf(
            Breed("b1", emptyList()),
            Breed("b2", emptyList()),
        )
    ),
    Breed("c", emptyList()),
)