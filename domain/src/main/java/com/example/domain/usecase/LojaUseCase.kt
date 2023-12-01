package com.example.domain.usecase

import com.example.domain.repository.ILojaReppsitory
import com.example.ifood_app.domain.model.Loja
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import javax.inject.Inject

class LojaUseCase @Inject constructor(
    private val lojaRepositoryImpl: ILojaReppsitory
){


  fun validarDadosLoja(loja : Loja): Boolean{
      val nome = loja.nome.validator()
          .minLength(3)
          .nonEmpty()
          .maxLength(32)
          .check()

      val razaoSocial = loja.razaoSocial.validator()
          .minLength(3)
          .nonEmpty()
          .maxLength(150)
          .check()



      val cnpj = loja.cnpj.validator()
          .nonEmpty()
          .check()


      val categoria = loja.categoria.toString().validator()
          .nonEmpty()
          .validNumber()
          .check()

      val especialidade = loja.especialidade.validator()
          .minLength(3)
          .nonEmpty()
          .minLength(32)
          .check()

      val imageCapa = loja.imageCapa.validator()
          .nonEmpty()
          .validUrl()
          .check()
      val fotoPefil = loja.fotoPerfil.validator()
          .nonEmpty()
          .validUrl()
          .check()

      if (nome) return false
      if (razaoSocial) return false
      if (cnpj) return false
      if (categoria) return false
      if (especialidade) return false
      if (fotoPefil) return false
      if (imageCapa) return false

      return true
  }

    suspend fun cadastrarLoja(loja :Loja):Boolean{
        return try {
            lojaRepositoryImpl.cadastrar(loja)
        }catch (e : Exception){
            e.printStackTrace()
          false
        }
    }
}