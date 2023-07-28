package hu.jozsefbarat.dogsshowcase.common

sealed class LoadingState<out T> {
    object Loading : LoadingState<Nothing>()
    data class Error(
        val message: String? = null,
        val throwable: Throwable? = null
    ) : LoadingState<Nothing>()
    data class Loaded<T>(val data: T) : LoadingState<T>()
}