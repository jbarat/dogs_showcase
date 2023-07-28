package hu.jozsefbarat.dogsshowcase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.jozsefbarat.data.breed.BreedsRepository
import hu.jozsefbarat.domain.breed.BreedsGateway
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBreedsGateway(
        breedsRepository: BreedsRepository
    ): BreedsGateway = breedsRepository
}

