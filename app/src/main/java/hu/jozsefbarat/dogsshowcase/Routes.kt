package hu.jozsefbarat.dogsshowcase

val routes = listOf(
    Routes.BreedsListScreen,
    Routes.BreedDetailsScreen
)
sealed class Routes(
    val canonicalRoute: String,
) {
    @Suppress("unused")
    fun getRoute(): String = canonicalRoute

    object BreedsListScreen : Routes("breeds")
    object BreedDetailsScreen : Routes("breeds/{breedName}") {
        object Params {
            const val breedName = "breedName"
        }
        fun getRoute(breedName: String): String = canonicalRoute.replace("{breedName}", breedName)
    }
}