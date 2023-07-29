package hu.jozsefbarat.dogsshowcase.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jozsefbarat.dogsshowcase.Routes
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.domain.breed.GetBreedImagesParams
import hu.jozsefbarat.domain.breed.GetBreedImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val randomImageLimit = 10

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBreedImagesUseCase: GetBreedImagesUseCase
) : ViewModel() {
    private val breedName: String =
        checkNotNull(savedStateHandle[Routes.BreedDetailsScreen.Params.breedName])

    private val _uiState = MutableStateFlow(BreedDetailsUiState())
    val uiState: StateFlow<BreedDetailsUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(breedName = breedName)

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = LoadingState.Loading)
            val images = getBreedImagesUseCase.execute(GetBreedImagesParams(breedName, randomImageLimit))

            _uiState.value = _uiState.value.copy(
                state = LoadingState.Loaded(images.map { it.url })
            )
        }
    }
}

