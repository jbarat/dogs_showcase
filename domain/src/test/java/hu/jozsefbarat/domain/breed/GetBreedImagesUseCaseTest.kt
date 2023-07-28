package hu.jozsefbarat.domain.breed

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetBreedImagesUseCaseTest {
    private lateinit var useCase: GetBreedImagesUseCase

    private val breedsGatewayMock = mockk<BreedsGateway>()

    @Before
    fun setup() {
        useCase = GetBreedImagesUseCase(breedsGatewayMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when empty value received then return it`() {
        coEvery { breedsGatewayMock.getBreedImages(breedName, limit) } returns emptyList()

        runBlocking {
            val result = useCase.execute(GetBreedImagesParams(breedName, limit))

            assert(result == emptyList<BreedImage>())
        }
    }

    @Test
    fun `when exception received then return it`() {
        coEvery {
            breedsGatewayMock.getBreedImages(
                breedName,
                limit
            )
        } throws Exception("mocked exception")

        runBlocking {
            try {
                useCase.execute(GetBreedImagesParams(breedName, limit))

                fail("Should throw exception")
            } catch (e: Exception) {
                assert(e.message == "mocked exception")
            }
        }
    }

    @Test
    fun `when correct values received then return them`() {
        coEvery { breedsGatewayMock.getBreedImages(breedName, limit) } returns response

        runBlocking {
            val result = useCase.execute(GetBreedImagesParams(breedName, limit))

            assert(result == response)
        }
    }
}

private val response= listOf(
    BreedImage("corgiImageUrl"),
    BreedImage("chihuahuaImageUrl"),
)

private const val breedName = "corgi"
private const val limit = 10