package com.example.ifood_app.domain.model

data class Loja(
    val nome :String,
    val fotoPerfil:String,
    val imageCapa:String,
    val categoria:Int,
    val razaoSocial:String,
    val cnpj:String,
    val email:String,
    val telefone:String,
    val especialidade:String,
)
