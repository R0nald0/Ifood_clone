package com.example.ifood_app.di

import com.example.domain.repository.IAutenticacaoRepository
import com.example.domain.usecase.AutenticaoUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {


    @Provides
    fun providerAuntenticaoUseCa(autenticaoRepositoryImpl: IAutenticacaoRepository): AutenticaoUseCase {
            return AutenticaoUseCase(autenticaoRepositoryImpl)
    }


//    @Provides
//    fun provideAuntenticaoRepository(firebaseAuth: FirebaseAuth):IAutenticacaoRepository{
//         return AutenticaoRepositoryImpl(firebaseAuth)
//    }

    @Provides
    fun provideFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}