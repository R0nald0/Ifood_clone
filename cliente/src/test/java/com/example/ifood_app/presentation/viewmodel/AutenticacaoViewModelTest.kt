package com.example.ifood_app.presentation.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecase.AutenticaoUseCase
import com.example.ifood_app.domain.model.Usuario
import com.example.ifood_app.domain.usecase.ResultadoAutenticao
import com.google.common.truth.Truth.assertThat
import com.jamiltondamasceno.projetotestesnapratica.utils.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AutenticacaoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var autenticacaoViewModel: AutenticacaoViewModel

    @Mock
     lateinit var autenticacaoUseCase: AutenticaoUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
       autenticacaoViewModel = AutenticacaoViewModel(autenticacaoUseCase)
    }



    @Test
    fun logarUsuario_logarUsuarioFirebase_retornar_verdadeiro() = runTest {

        val usuario =Usuario("teste1","email@gmaiil.com","113213214","313627373")
        Mockito.`when`(autenticacaoUseCase.validarLoginUsuario(usuario))
            .thenReturn(ResultadoAutenticao())

        Mockito.`when`(autenticacaoUseCase.logarUsuario(usuario))
            .thenReturn(true)
        autenticacaoViewModel.logarUsuario(usuario)

        val retorno =  autenticacaoViewModel.sucesso.getOrAwaitValue ()
        assertThat(retorno).isTrue()
    }

    @Test
    fun cadastrUsuario_cadastraUsuarioFirebase_retornar_verdadeiro() = runTest {

        val usuario =Usuario("teste1","email@gmaiil.com","113213214","313627373")
        Mockito.`when`(autenticacaoUseCase.validarCadastroUsuario(usuario))
            .thenReturn(ResultadoAutenticao())

        Mockito.`when`(autenticacaoUseCase.cadastrarUsuario(usuario))
            .thenReturn(true)
       autenticacaoViewModel.cadastroUsuario(usuario)

       val retorno =  autenticacaoViewModel.sucesso.getOrAwaitValue ()
        assertThat(retorno).isTrue()
    }

    @After
    fun tearDown() {
    }
}