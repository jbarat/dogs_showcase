package hu.jozsefbarat.dogsshowcase.screens.list

import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.domain.breed.Breed

data class BreedsListUiState(
    val breeds: LoadingState<List<Breed>> = LoadingState.Loading,
)