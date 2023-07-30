@file:OptIn(ExperimentalMaterial3Api::class)

package hu.jozsefbarat.dogsshowcase.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hu.jozsefbarat.dogsshowcase.R
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.dogsshowcase.common.shimmerBrush
import hu.jozsefbarat.dogsshowcase.ext.capitalize
import hu.jozsefbarat.domain.breed.Breed

@Composable
fun BreedsListScreen(
    viewModel: BreedListViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    BreedsListContent(uiState, onBreedSelected = { breed ->
        viewModel.onAction(BreedsListAction.BreedSelected(breed, navController))
    })
}

@Composable
fun BreedsListContent(
    uiState: BreedsListUiState,
    onBreedSelected: (Breed) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_breeds_list),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (uiState.breeds) {
                    LoadingState.Loading -> {
                        LoadingAnimation()
                    }

                    is LoadingState.Error -> {

                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(Color.White)
                        )
                        {
                            Text(text = uiState.breeds.message ?: stringResource(R.string.unknown_error))
                        }

                    }

                    is LoadingState.Loaded -> {
                        BreedsList(breeds = uiState.breeds.data, onClick = onBreedSelected)
                    }
                }

            }
        }
    )
}

@Composable
fun LoadingAnimation() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(30) {
            Box(
                modifier = Modifier
                    .testTag("shimmer $it")
                    .background(shimmerBrush(targetValue = 1000f))
                    .fillMaxWidth()
                    .height(64.dp)
            )
        }
    }
}


@Composable
fun BreedsList(breeds: List<Breed>, onClick: (Breed) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(breeds.size) { breed ->
            BreedListItem(breed = breeds[breed], onClick = onClick)
        }
    }
}

@Composable
fun BreedListItem(breed: Breed, onClick: (Breed) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick(breed) },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        headlineText = {
            Text(
                text = breed.name.capitalize(),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        },
        supportingText = { Text(text = stringResource(R.string.list_item_sub_text_breeds, breed.subBreeds.size)) },
        trailingContent = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null
            )
        },
    )

}
