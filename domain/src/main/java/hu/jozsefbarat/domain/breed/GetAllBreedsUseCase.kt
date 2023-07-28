package hu.jozsefbarat.domain.breed

import hu.jozsefbarat.domain.common.UseCase
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(
    private val breedsGateway: BreedsGateway
) : UseCase<List<Breed>> {
    override suspend fun execute() = breedsGateway.getAllDogBreeds()
        .sortedBy { it.name }
}

