package hu.jozsefbarat.dogsshowcase.screens.detail

import hu.jozsefbarat.dogsshowcase.common.LoadingState

data class BreedDetailsUiState(
    val state: LoadingState<List<String>> = LoadingState.Loading,
    val breedName: String? = null,
)