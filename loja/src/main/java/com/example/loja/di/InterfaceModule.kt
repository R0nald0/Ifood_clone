package com.example.loja.di

import com.example.domain.repository.IAutenticacaoRepository
import com.example.domain.repository.ILojaReppsitory
import com.example.domain.repository.LojaRepositoryImpl
import com.example.loja.data.remote.firebase.repository.repositoryimpl.AutenticaoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindsIAutenticacaoRepository(autenticaoRepositoryImpl: AutenticaoRepositoryImpl): IAutenticacaoRepository

    @Binds
    abstract fun bindsLojaRepository(lojaRepositoryImpl: LojaRepositoryImpl): ILojaReppsitory

}