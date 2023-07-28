package hu.jozsefbarat.domain.breed

data class Breed(
    val name: String,
    val subBreeds: List<Breed> = emptyList()
)