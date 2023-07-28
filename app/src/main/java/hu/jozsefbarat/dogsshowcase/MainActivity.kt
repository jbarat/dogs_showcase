package hu.jozsefbarat.dogsshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.jozsefbarat.dogsshowcase.screens.detail.BreedDetailsScreen
import hu.jozsefbarat.dogsshowcase.screens.list.BreedsListScreen
import hu.jozsefbarat.dogsshowcase.theme.DogsShowCaseTheme

val routes = listOf(
    Routes.BreedsListScreen,
    Routes.BreedDetailsScreen
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            DogsShowCaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.BreedsListScreen.canonicalRoute
                    ) {
                        routes.forEach { route ->
                            when (route) {
                                Routes.BreedsListScreen -> composable(route = route.canonicalRoute) {
                                    BreedsListScreen(hiltViewModel(), navController)
                                }

                                Routes.BreedDetailsScreen -> composable(route = route.canonicalRoute) {
                                    BreedDetailsScreen(hiltViewModel(), navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}