package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.domain.breed.Breed
import org.junit.Rule
import org.junit.Test

class BreedListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showLoading() {
        composeTestRule.setContent {
            BreedsListContent(BreedsListUiState(LoadingState.Loading)) {}
        }

        composeTestRule.onNodeWithTag("shimmer 0").assertIsDisplayed()
    }

    @Test
    fun showError() {
        composeTestRule.setContent {
            BreedsListContent(BreedsListUiState(LoadingState.Error("Error"))) {}
        }

        composeTestRule.onNodeWithText("Error").assertIsDisplayed()
    }

    @Test
    fun showList() {
        composeTestRule.setContent {
            BreedsListContent(
                BreedsListUiState(
                    LoadingState.Loaded(
                        data = listOf(
                            Breed("corgi", emptyList()),
                            Breed("pembroke", emptyList()),
                            Breed(
                                "cardigan", emptyList()
                            )
                        )
                    )
                )
            ) {}
        }

        composeTestRule.onNodeWithText("Corgi").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pembroke").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cardigan").assertIsDisplayed()
    }
}