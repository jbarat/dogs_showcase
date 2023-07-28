package hu.jozsefbarat.data.breed

import hu.jozsefbarat.domain.breed.Breed
import hu.jozsefbarat.domain.breed.BreedImage
import hu.jozsefbarat.domain.breed.BreedsGateway
import javax.inject.Inject

class BreedsRepository @Inject constructor(
    private val breedsApiService: BreedsApiService
) : BreedsGateway {

    override suspend fun getAllDogBreeds(): List<Breed> {
        val result = breedsApiService.getAllBreeds()

        if (result.status == "success") {
            return result.message?.map {
                Breed(name = it.key, subBreeds = it.value.map { Breed(name = it) })
            } ?: emptyList()
        } else {
            throw Exception("Error getting breeds ${result.status}")
        }
    }

    override suspend fun getBreedImages(breedId: String, limit: Int): List<BreedImage> {
        val result = breedsApiService.getBreedImages(breedId, limit)

        if (result.status == "success") {
            return result.message?.map { BreedImage(url = it) } ?: emptyList()
        } else {
            throw Exception("Error getting breed images ${result.status}")
        }
    }
}