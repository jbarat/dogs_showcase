package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import hu.jozsefbarat.domain.breed.Breed
import org.junit.Rule
import org.junit.Test

class BreedsListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun drawList() {
        composeTestRule.setContent {
            BreedsList(
                breeds,
                onClick = {}
            )

        }

        composeTestRule.onNodeWithText("Corgi").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pembroke").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cardigan").assertIsDisplayed()
    }

    @Test
    fun delegateClick(){
        composeTestRule.setContent {
            var selectedBreed by remember { mutableStateOf("") }

            BreedsList(
               breeds
            ) {
                selectedBreed = it.name
            }

            Text(text = "Selected breed: $selectedBreed")
        }

        composeTestRule.onNodeWithText("Corgi").performClick()
        composeTestRule.onNodeWithText("Selected breed: corgi").assertIsDisplayed()
    }
}

private val breeds = listOf(
    Breed("corgi", emptyList()),
    Breed("pembroke", emptyList()),
    Breed("cardigan", emptyList())
)
