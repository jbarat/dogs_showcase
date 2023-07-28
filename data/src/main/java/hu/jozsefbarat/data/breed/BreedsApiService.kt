package hu.jozsefbarat.data.breed

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApiService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedsListResponse

    @GET("breed/{breedName}/images/random/{imagesCount}")
    suspend fun getBreedImages(
        @Path("breedName") breedName: String,
        @Path("imagesCount") imagesCount: Int,
    ): BreedImagesResponse
}

data class BreedsListResponse(
    @SerializedName("message") val message: Map<String, List<String>>?,
    @SerializedName("status") val status: String,
)

data class BreedImagesResponse(
    @SerializedName("message") val message: List<String>?,
    @SerializedName("status") val status: String,
)