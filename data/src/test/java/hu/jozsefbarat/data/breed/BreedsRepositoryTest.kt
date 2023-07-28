package hu.jozsefbarat.data.breed

import hu.jozsefbarat.domain.breed.Breed
import hu.jozsefbarat.domain.breed.BreedImage
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class BreedsRepositoryTest {
    private lateinit var repository: BreedsRepository

    private val breedsApiServiceMock = mockk<BreedsApiService>()

    @Before
    fun setup() {
        repository = BreedsRepository(breedsApiServiceMock)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when getAllBreeds is successful but empty then return empty`() {
        coEvery { breedsApiServiceMock.getAllBreeds() } returns successfulEmptyAllBreedsResponse

        runBlocking {
            val result = repository.getAllDogBreeds()

            assert(result == emptyList<Breed>())
        }
    }

    @Test
    fun `when getAllBreeds is successful then return breeds`() {
        coEvery { breedsApiServiceMock.getAllBreeds() } returns successfulAllBreedsResponse

        runBlocking {
            val result = repository.getAllDogBreeds()

            assert(
                result == listOf(
                    Breed(
                        name = "breed1",
                        subBreeds = listOf(Breed(name = "subBreed1"), Breed(name = "subBreed2"))
                    ),
                    Breed(name = "breed2", subBreeds = listOf()),
                    Breed(name = "breed3", subBreeds = listOf()),
                )
            )
        }
    }

    @Test
    fun `when getAllBreeds is unsuccessful then throw exception`() {
        coEvery { breedsApiServiceMock.getAllBreeds() } returns unsuccessfulAllBreedsResponse

        runBlocking {
            try {
                repository.getAllDogBreeds()

                assert(false)
            } catch (e: Exception) {
                assert(e.message == "Error getting breeds error")
            }
        }
    }

    @Test
    fun `when getBreedImages is successful but empty then return empty`() {
        coEvery {
            breedsApiServiceMock.getBreedImages(
                breedName,
                limit
            )
        } returns successfulEmptyBreedImagesResponse

        runBlocking {
            val result = repository.getBreedImages(breedName, limit)

            assert(result == emptyList<BreedImage>())
        }
    }

    @Test
    fun `when getBreedImages is successful then return images`() {
        coEvery {
            breedsApiServiceMock.getBreedImages(
                breedName,
                limit
            )
        } returns successfulBreedImagesResponse

        runBlocking {
            val result = repository.getBreedImages(breedName, limit)

            assert(
                result == listOf(
                    BreedImage(url = "url1"),
                    BreedImage(url = "url2"),
                    BreedImage(url = "url3"),
                )
            )
        }
    }

    @Test
    fun `when getBreedImages is unsuccessful then throw exception`() {
        coEvery {
            breedsApiServiceMock.getBreedImages(
                breedName,
                limit
            )
        } returns unsuccessfulBreedImagesResponse

        runBlocking {
            try {
                repository.getBreedImages(breedName, limit)

                assert(false)
            } catch (e: Exception) {
                assert(e.message == "mocked exception")
            }
        }
    }
}

val successfulEmptyAllBreedsResponse = BreedsListResponse(
    status = "success",
    message = emptyMap()
)

val successfulAllBreedsResponse = BreedsListResponse(
    status = "success",
    message = mapOf(
        "breed1" to listOf("subBreed1", "subBreed2"),
        "breed2" to listOf(),
        "breed3" to listOf(),
    )
)


val unsuccessfulAllBreedsResponse = BreedsListResponse(
    status = "error",
    message = null
)

val successfulEmptyBreedImagesResponse = BreedImagesResponse(
    status = "success",
    message = emptyList()
)

val successfulBreedImagesResponse = BreedImagesResponse(
    status = "success",
    message = listOf("url1", "url2", "url3")
)

val unsuccessfulBreedImagesResponse = BreedImagesResponse(
    status = "error",
    message = null
)

private const val breedName = "corgi"
private const val limit = 10