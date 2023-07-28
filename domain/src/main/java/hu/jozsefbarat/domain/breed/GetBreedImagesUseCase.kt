package hu.jozsefbarat.domain.breed

import hu.jozsefbarat.domain.common.UseCaseWithParams
import javax.inject.Inject

data class GetBreedImagesParams(
    val breedName: String,
    val limit: Int
)

class GetBreedImagesUseCase @Inject constructor(
    private val breedRepository: BreedsGateway
) : UseCaseWithParams<List<BreedImage>, GetBreedImagesParams> {

    override suspend fun execute(params: GetBreedImagesParams): List<BreedImage> {
        return breedRepository.getBreedImages(params.breedName, params.limit)
    }
}