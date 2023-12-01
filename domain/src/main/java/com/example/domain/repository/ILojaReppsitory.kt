package com.example.domain.repository

import com.example.ifood_app.domain.model.Loja

interface ILojaReppsitory {
    suspend fun cadastrar(Loja : Loja):Boolean
}