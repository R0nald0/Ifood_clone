package com.example.domain.repository

import com.example.ifood_app.domain.model.Loja
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LojaRepositoryImpl @Inject constructor(
    private val auth :FirebaseFirestore
): ILojaReppsitory {
    override suspend fun cadastrar(Loja: Loja): Boolean {
        //TODO cadastra usuario
        return  true
    }
}