package com.example.ifood_app.data.remote.firebase.repository

import com.example.ifood_app.domain.model.Usuario

interface iAutenticacaoRepository {
    suspend fun cadastrarUsuario(usuario: Usuario):Boolean
    suspend  fun logarUsuario(usuario: Usuario):Boolean
    suspend  fun isLogged():Boolean
}