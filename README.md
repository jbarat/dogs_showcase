# Breeds Showcase App 🐶

The Breeds Showcase App is a simple application that displays a list of dog breeds along with 10
random pictures for
each breed. The data is fetched from the [Dog API](https://dog.ceo/dog-api/).


https://github.com/jbarat/dogs_showcase/assets/6706968/e6a76e7c-3c09-4d8d-8197-56b3f47e8cea


## App Architecture

The app uses clean architecture. The layers are separated into different Gradle modules to enforce
the separation of concerns and the dependency rule.(i.e. in the domain layer it is not possible to
use any class from
the presentation layer). A violation of this is the UI layer which has access to all the other
layers (
it should only have access to domain and not data).
The reason for this is that, due to certain particularities of android it is much simpler to have
the DI,
navigation and the UI in the same module.

The layers in more details:

* **domain layer** contains the business logic of the app. It defines the usecases, the
  models and the gateways to interact with other parts of the app.
* **data layer** contains the implementation of the gateways defined in the domain layer. It
  also contains the models that are used to interact with the REST API.
* **presentation layer** contains the UI-related code. The pattern used is MVVM. The UI is
  implemented using Jetpack Compose. It also contains the view models that are used to interact with
  the domain layer.

## Testing

* **domain layer** is the simplest to test. It contains only pure kotlin code. The tests are
  written using JUnit and Mockk.
* **data layer** in this case is also simple to test. It contains only the implementation of the
  gateways defined in the domain layer. The tests are written using JUnit and Mockk.
* **presentation layer** is the most complex to test as it requires instrumentation tests.
    * **ViewModels** use a simple unidirectional flow, so most tests verify if the actions cause the
      expected state
      changes. However, as the app is relatively simple, there are few examples of this.
    * **Compose UI** This one I have found interesting. If we have a top layer composable where we
      take the state from
      the
      viewmodel and pass it down and make all the callbacks to propagate back to this one composable
      i.e. we make the
      actual ui "view model free" then testing the ui became very simple. Every test is about having
      a prepared state
      object and checking if the ui is rendered correctly. An example of this "ViewModel free" UI is
      the

```kotlin
@Composable
fun BreedDetailsScreen(viewModel: BreedDetailsViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    BreedDetailsContent(uiState, onBackTapped = {
        navController.popBackStack()
    })
}
```

## Missing things

* **Navigation** as this app has no XML you can't easily "find" the navigation graph. I don't find a
  satisfactory way to
  make the nav graph or some sort of wrapper injectable in the view models.
* **How to test clicks in compose?** The only way I have found to do it is with creating a fake UI
  element which changes
  if the callback is called. I'm sure there is a better way. Here is an example of the current
  approach:

```kotlin
  @Test
fun delegateClick() {
    composeTestRule.setContent {
        val testingFlag by remember { mutableStateOf(false) }

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
```

* Not everything is tested and there are no integration tests between the screens

## Noteworthy Libraries used

* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for
  asynchronous programming
* [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI
* [Hilt](https://dagger.dev/hilt/) for dependency injection
* [Navigation](https://developer.android.com/jetpack/compose/navigation) for navigation
* [Coil](https://coil-kt.github.io/coil/) for image loading
* [Retrofit 2](https://github.com/square/retrofit) and [OkHttp](https://github.com/square/okhttp)
  for
  networking
* [Mockk](https://mockk.io/) for mocking in tests
