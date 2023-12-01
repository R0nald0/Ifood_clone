package com.example.ifood_app.data.remote.firebase.repository.repositoryimpl

import com.example.domain.repository.IAutenticacaoRepository
import com.example.ifood_app.domain.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticaoRepositoryImpl @Inject constructor(
    private val auth  : FirebaseAuth
) :IAutenticacaoRepository {

    override suspend fun cadastrarUsuario(usuario: Usuario):Boolean {
      return  auth.createUserWithEmailAndPassword(
            usuario.email,
            usuario.senha
        ).await() != null
    }

    override suspend fun logarUsuario(usuario: Usuario): Boolean {
        return  auth.signInWithEmailAndPassword(
            usuario.email,
            usuario.senha
        ).await() != null
    }

    override suspend fun isLogged(): Boolean {
        return auth.currentUser != null
    }


}