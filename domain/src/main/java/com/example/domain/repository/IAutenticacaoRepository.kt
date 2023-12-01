package com.example.domain.repository

import com.example.ifood_app.domain.model.Usuario


interface IAutenticacaoRepository {
    suspend fun cadastrarUsuario(usuario: Usuario):Boolean
    suspend  fun logarUsuario(usuario: Usuario):Boolean
    suspend  fun isLogged():Boolean
}