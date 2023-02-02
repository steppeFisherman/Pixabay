package com.example.pixabay.ui.di

import com.example.pixabay.domain.Repository
import com.example.pixabay.domain.usecases.FetchUseCase
import com.example.pixabay.ui.model.MapDomainToUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideFetchItemsUseCase(repository: Repository): FetchUseCase =
        FetchUseCase.Base(repository = repository)

    @Provides
    fun provideMapDomainToApp(): MapDomainToUi = MapDomainToUi.Base()

}