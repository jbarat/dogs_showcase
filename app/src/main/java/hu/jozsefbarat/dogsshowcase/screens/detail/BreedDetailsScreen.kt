@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package hu.jozsefbarat.dogsshowcase.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import hu.jozsefbarat.dogsshowcase.R
import hu.jozsefbarat.dogsshowcase.common.LoadingState
import hu.jozsefbarat.dogsshowcase.common.shimmerBrush
import hu.jozsefbarat.dogsshowcase.ext.capitalize

@Composable
fun BreedDetailsScreen(viewModel: BreedDetailsViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    BreedDetailsContent(uiState, onBackTapped = {
        navController.popBackStack()
    })
}

@Composable
fun BreedDetailsContent(
    uiState: BreedDetailsUiState,
    onBackTapped: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            R.string.title_breed_details,
                            uiState.breedName?.capitalize() as String
                        ),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onBackTapped) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.button_back),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (uiState.breedImages) {
                    LoadingState.Loading -> LoadingAnimation()

                    is LoadingState.Error -> {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(Color.White)
                        )
                        {
                            Text(
                                text = uiState.breedImages.message
                                    ?: stringResource(R.string.unknown_error)
                            )
                        }
                    }

                    is LoadingState.Loaded -> {
                        ImagesContent(uiState.breedImages.data)
                    }
                }
            }
        }
    )
}

@Composable
private fun ImagesContent(images: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(images.size) { index ->
                val photo = images[index]
                AsyncImage(
                    model = photo,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .background(shimmerBrush(targetValue = 1000f))
                        .fillMaxWidth()
                        .height(150.dp),
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun LoadingAnimation() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(randomImageLimit) {
                Box(
                    modifier = Modifier
                        .background(shimmerBrush(targetValue = 1000f))
                        .fillMaxWidth()
                        .height(150.dp),
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}