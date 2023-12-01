package com.example.domain.usecase

import com.example.domain.repository.IAutenticacaoRepository
import com.example.ifood_app.domain.model.Usuario
import com.example.ifood_app.domain.usecase.ResultadoAutenticao
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import javax.inject.Inject

class AutenticaoUseCase @Inject constructor(
     private val autenticacaoRepository:IAutenticacaoRepository
) {

    fun validarCadastroUsuario(usuario: Usuario): ResultadoAutenticao {
       val resultadoAutenticao  = ResultadoAutenticao()

        if (!usuario.nome.nonEmpty())resultadoAutenticao.nomeInvalid =true

        if (!usuario.email.validEmail())resultadoAutenticao.emailInvalid =true

          val senha = usuario.senha.validator()
            .nonEmpty().minLength(5).check()

        if (!senha)resultadoAutenticao.senhaInvalid =true

        if (!usuario.email.nonEmpty())resultadoAutenticao.emailInvalid =true

        if (!usuario.telefone.nonEmpty())resultadoAutenticao.telefoneInvalid =true
        return  resultadoAutenticao
    }

    fun validarLoginUsuario(usuario: Usuario): ResultadoAutenticao {
        val resultadoAutenticao  = ResultadoAutenticao()

        val senha = usuario.senha.validator().nonEmpty().minLength(5).check()
        val email = usuario.email.validEmail()

        if (!senha)resultadoAutenticao.senhaInvalid =true

        if (!email)resultadoAutenticao.emailInvalid =true
        if (!usuario.email.nonEmpty())resultadoAutenticao.emailInvalid =true


        return  resultadoAutenticao
    }

   suspend fun logarUsuario(usuario: Usuario):Boolean{
       try {
           return  autenticacaoRepository.logarUsuario(usuario)
       }catch (ex: Exception){
           ex.printStackTrace()
           return false
       }
   }


    suspend fun cadastrarUsuario(usuario: Usuario) : Boolean{
       try {
           return  autenticacaoRepository.cadastrarUsuario(usuario)
       }catch (ex: Exception){
           ex.printStackTrace()
         return false
       }
    }
    suspend fun isLogged():Boolean{
         return try {
               autenticacaoRepository.isLogged()
          }catch (ex :Exception){
              ex.printStackTrace()
              return false
          }
    }
}