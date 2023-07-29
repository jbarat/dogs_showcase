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

class BreedsListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun drawMainText() {
        composeTestRule.setContent {
            BreedListItem(
                breed = Breed(
                    "corgi", listOf(
                        Breed("pembroke", emptyList()),
                        Breed("cardigan", emptyList())
                    )
                )
            ) {
            }
        }

        composeTestRule.onNodeWithText("Corgi").assertIsDisplayed()
    }

    @Test
    fun drawSubText() {
        composeTestRule.setContent {
            BreedListItem(
                breed = Breed(
                    "corgi", listOf(
                        Breed("pembroke", emptyList()),
                        Breed("cardigan", emptyList())
                    )
                )
            ) {
            }
        }

        composeTestRule.onNodeWithText("Sub breeds 2").assertIsDisplayed()
    }

    @Test
    fun delegateClick() {
        composeTestRule.setContent {
            var testingFlag by remember { mutableStateOf(false) }

            BreedListItem(
                breed = Breed(
                    "corgi", listOf(
                        Breed("pembroke", emptyList()),
                        Breed("cardigan", emptyList())
                    )
                )
            ) {
                testingFlag = true
            }

            Text(text = if (testingFlag) "Clicked" else "Not clicked")
        }

        composeTestRule.onNodeWithText("Corgi").performClick()
        composeTestRule.onNodeWithText("Clicked").assertIsDisplayed()
    }
}