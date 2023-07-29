package hu.jozsefbarat.dogsshowcase.screens.list

import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.domain.breed.GetAllBreedsUseCase
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test

class BreedListViewModelTest {
    private lateinit var viewModel: BreedListViewModel

    private val getAllBreedsUseCase = mockk<GetAllBreedsUseCase>()

    @Before
    fun setUp() {
        viewModel = BreedListViewModel(getAllBreedsUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when init then uiState is loading`() {
        val state = viewModel.uiState

        assert(state.value.state is LoadingState.Loading)
    }
}