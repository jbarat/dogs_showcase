package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jozsefbarat.dogsshowcase.Routes
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.domain.breed.GetAllBreedsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BreedsListUiState())
    val uiState: StateFlow<BreedsListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val breeds = getAllBreedsUseCase.execute()
            _uiState.value = _uiState.value.copy(breeds = LoadingState.Loaded(breeds))
        }
    }

    fun onAction(action: BreedsListAction) {
        when (action) {
            is BreedsListAction.BreedSelected -> {
                action.navController.navigate(
                    Routes.BreedDetailsScreen.getRoute(action.breed.name)
                )
            }
        }
    }
}
