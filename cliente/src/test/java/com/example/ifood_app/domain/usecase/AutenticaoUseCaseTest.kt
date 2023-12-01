package com.example.ifood_app.domain.usecase

import com.example.domain.usecase.AutenticaoUseCase
import com.example.ifood_app.data.remote.firebase.repository.iAutenticacaoRepository
import com.example.ifood_app.domain.model.Usuario
import com.google.common.truth.Truth.assertThat


import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AutenticaoUseCaseTest {


    @Mock
     lateinit var autenticacaoRepository: iAutenticacaoRepository

     private lateinit var autenticacaoUseCase: AutenticaoUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        autenticacaoUseCase = com.example.domain.usecase.AutenticaoUseCase(autenticacaoRepository)
    }

    @Test
    fun validarCadastroUsuario_validaDadosPreenchidosCorretamente_retornarVerdadeiro() {
        val usuario = Usuario("teste1","email@gmaiil.com","113213214","")

     val  resultado =   autenticacaoUseCase.validarCadastroUsuario(usuario)
        assertThat(resultado.sucessoCadastro).isTrue()

    }
    @Test
    fun validarCadastroUsuario_validaNomeInvalido_retornarFalse() {
        val usuario = Usuario("asdsdadsa","email@gmaiil.com","113213214","313627373")

        val  resultado =   autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultado.sucessoCadastro).isFalse()

    }
    @Test
    fun validarCadastroUsuario_validaEmailInvalido_retornarFalse() {
        val usuario = Usuario("teste1","","113213214","313627373")

        val  resultado =   autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultado.sucessoCadastro).isFalse()

    }
    @Test
    fun validarCadastroUsuario_validaSenhaInvalido_retornarFalso() {
        val usuario = Usuario("teste1","email@gmaiil.com","12345612","313627373")

        val  resultado =   autenticacaoUseCase.validarCadastroUsuario(usuario)
        assertThat(resultado.sucessoCadastro).isFalse()
    }
    @Test
    fun validarCadastroUsuario_validaTelefoneInvalido_retornarFalso() {
        val usuario = Usuario("teste1","email@gmaiil.com","dasdsaewe","43242434234")

        val  resultado =   autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultado.sucessoCadastro).isFalse()
    }

    @After
    fun tearDown() {
    }
}