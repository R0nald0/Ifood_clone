package com.example.ifood_app.di

import com.example.domain.repository.IAutenticacaoRepository
import com.example.ifood_app.data.remote.firebase.repository.repositoryimpl.AutenticaoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindsIAutenticacaoRepository(autenticaoRepositoryImpl: AutenticaoRepositoryImpl
    ):IAutenticacaoRepository
}