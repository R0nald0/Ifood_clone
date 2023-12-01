package com.example.loja.di


import com.example.domain.repository.IAutenticacaoRepository
import com.example.domain.repository.ILojaReppsitory
import com.example.domain.repository.LojaRepositoryImpl
import com.example.domain.usecase.AutenticaoUseCase
import com.example.domain.usecase.LojaUseCase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {


    @Provides
    fun providerAuntenticaoUseCase(autenticaoRepositoryImpl: IAutenticacaoRepository): AutenticaoUseCase {
            return AutenticaoUseCase(autenticaoRepositoryImpl)
    }
    @Provides
    fun providerLojaUseCase(lojaRepository: ILojaReppsitory):LojaUseCase {
        return LojaUseCase(lojaRepository)
    }

    @Provides
    fun providerLojaRepositoryImpl(fireStore :FirebaseFirestore ):LojaRepositoryImpl {
        return LojaRepositoryImpl(fireStore)
    }

//    Injeção duplicada -> instance module
//    @Provides
//    fun provideAuntenticaoRepository(firebaseAuth: FirebaseAuth): IAutenticacaoRepository {
//         return AutenticaoRepositoryImpl(firebaseAuth)
//    }

    @Provides
    fun provideFirebaseFireStore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}