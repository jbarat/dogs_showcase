package hu.jozsefbarat.domain.breed

interface BreedsGateway {
    suspend fun getAllDogBreeds(): List<Breed>
    suspend fun getBreedImages(breedId: String, limit: Int): List<BreedImage>
}