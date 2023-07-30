package hu.jozsefbarat.dogsshowcase.screens.detail

import hu.jozsefbarat.dogsshowcase.common.LoadingState

data class BreedDetailsUiState(
    val breedImages: LoadingState<List<String>> = LoadingState.Loading,
    val breedName: String? = null,
)