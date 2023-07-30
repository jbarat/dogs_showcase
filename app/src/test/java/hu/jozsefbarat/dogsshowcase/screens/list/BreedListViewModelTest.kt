package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.navigation.NavController
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.dogsshowcase.test.MainCoroutineRule
import hu.jozsefbarat.domain.breed.Breed
import hu.jozsefbarat.domain.breed.GetAllBreedsUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedListViewModelTest {
    private val getAllBreedsUseCase = mockk<GetAllBreedsUseCase>()
    private val navController = mockk<NavController>(relaxed = true)


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when init then uiState is loading`() {
        val state = getViewModel().uiState

        assert(state.value.breeds is LoadingState.Loading)
    }

    @Test
    fun `when receive breeds then change state to loaded`() {
        coEvery { getAllBreedsUseCase.execute() } returns emptyList()

        val state = getViewModel().uiState

        assert(state.value.breeds is LoadingState.Loaded)
    }

    @Test
    fun `when receive breeds then add them to the state`() {
        coEvery { getAllBreedsUseCase.execute() } returns breedsResponse

        val state = getViewModel().uiState

        assert((state.value.breeds as LoadingState.Loaded).data == breedsResponse)
    }

    @Test
    fun `when BreedSelected action happens then navigate to the BreedDetailsScreen`() {
        coEvery { getAllBreedsUseCase.execute() } returns emptyList()

        val viewModel = getViewModel()

        viewModel.onAction(BreedsListAction.BreedSelected(Breed(name = "corgi"), navController))

        verify(exactly = 1) { navController.navigate("breeds/corgi") }
    }

    private fun getViewModel() = BreedListViewModel(
        getAllBreedsUseCase = getAllBreedsUseCase
    )
}

val breedsResponse = listOf(
    Breed(
        name = "breed1",
        subBreeds = listOf(Breed(name = "subBreed1"), Breed(name = "subBreed2"))
    ),
    Breed(name = "breed2", subBreeds = listOf()),
    Breed(name = "breed3", subBreeds = listOf()),
)

