package hu.jozsefbarat.dogsshowcase.screens.detail

import androidx.lifecycle.SavedStateHandle
import hu.jozsefbarat.dogsshowcase.Routes
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.dogsshowcase.test.MainCoroutineRule
import hu.jozsefbarat.domain.breed.BreedImage
import hu.jozsefbarat.domain.breed.GetBreedImagesParams
import hu.jozsefbarat.domain.breed.GetBreedImagesUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedDetailsViewModelTest {
    private val getBreedImagesUseCase = mockk<GetBreedImagesUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when breedName passed in then it should be put into the state`() {
        every { savedStateHandle.get<String>(Routes.BreedDetailsScreen.Params.breedName) } returns "corgi"

        val state = getViewModel().uiState

        assert(state.value.breedName == "corgi")
    }

    @Test
    fun `when init then uiState is loading`() {
        every { savedStateHandle.get<String>(Routes.BreedDetailsScreen.Params.breedName) } returns "corgi"

        val state = getViewModel().uiState

        assert(state.value.state is LoadingState.Loading)
    }


    @Test
    fun `when receive images then change state to loaded`() {
        every { savedStateHandle.get<String>(Routes.BreedDetailsScreen.Params.breedName) } returns "corgi"
        coEvery {
            getBreedImagesUseCase.execute(
                GetBreedImagesParams("corgi", 10)
            )
        } returns emptyList()

        val state = getViewModel().uiState

        assert(state.value.state is LoadingState.Loaded)
    }

    @Test
    fun `when receive images then add them to the state`() {
        every { savedStateHandle.get<String>(Routes.BreedDetailsScreen.Params.breedName) } returns "corgi"
        coEvery {
            getBreedImagesUseCase.execute(
                GetBreedImagesParams("corgi", 10)
            )
        } returns imagesResponse

        val state = getViewModel().uiState
        assert((state.value.state as LoadingState.Loaded).data == mappedResponse)
    }

    private fun getViewModel() = BreedDetailsViewModel(
        getBreedImagesUseCase = getBreedImagesUseCase,
        savedStateHandle = savedStateHandle,
    )
}

private val imagesResponse = listOf(
    BreedImage("cute Corgi"),
    BreedImage("other cute Corgi"),
    BreedImage("another cute Corgi"),
    BreedImage("yet another cute Corgi"),
    BreedImage("and another cute Corgi"),
)

val mappedResponse = listOf(
    "cute Corgi",
    "other cute Corgi",
    "another cute Corgi",
    "yet another cute Corgi",
    "and another cute Corgi",
)
