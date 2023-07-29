package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.navigation.NavController
import hu.jozsefbarat.domain.breed.Breed

sealed class BreedsListAction {
    data class BreedSelected(val breed: Breed, val navController: NavController) :
        BreedsListAction()
}