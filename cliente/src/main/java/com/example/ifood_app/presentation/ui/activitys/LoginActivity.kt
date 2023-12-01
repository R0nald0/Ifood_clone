package com.example.ifood_app.presentation.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.core.AlertaMensagem
import com.example.core.esconderTeclado
import com.example.core.exibirMensagem
import com.example.ifood_app.databinding.ActivityLoginBinding
import com.example.ifood_app.domain.model.Usuario

import com.example.ifood_app.presentation.viewmodel.AutenticacaoViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    val auth = FirebaseAuth.getInstance()
    private val autenticacaoViewModel :AutenticacaoViewModel by viewModels()
    private val alertMensagem by lazy{
         AlertaMensagem(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
         //auth.signOut()
        inicializar()

    }

    private fun inicializar() {
        inicializarEventoClick()
        inicializarObservables()
    }

    override fun onStart() {
        super.onStart()
        autenticacaoViewModel.isLogged()
    }

    private fun inicializarObservables() {
        autenticacaoViewModel.resultadoValidacao.observe(this){
            with(binding){
                edtEmail.error =
                    if (it.emailInvalid)"preencha o Email" else null
                edtLoginSenha.error =
                    if (it.senhaInvalid)"preencha o Senha" else null
            }
        }

        autenticacaoViewModel.sucesso.observe(this){
            if (it){
                navegarParaTelaPrincipal()
                exibirMensagem("Sucesso ao Logar")
            }else{
                limparCampos()
                exibirMensagem("Erro ao logar,Verique email e senha")
            }
        }
        autenticacaoViewModel.isLogged.observe(this){isLogged ->
            if (isLogged) {
                exibirMensagem("Entrando...")
                navegarParaTelaPrincipal()
            }

        }
        autenticacaoViewModel.carregando.observe(this){carregando ->
            if (carregando){
                alertMensagem.call("Validando Login..")
            }else{
                alertMensagem.hide()
            }
        }
    }

    private fun limparCampos() {
        binding.edtLoginSenha.setText("")
        binding.edtLoginSenha.setText("")
    }

    private fun navegarParaTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun inicializarEventoClick(){
        with(binding){

            btnCadastre.setOnClickListener {
                  startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
            }
            btnLogin.setOnClickListener { view->
                 view.esconderTeclado()
                edtEmail.clearFocus()
                edtLoginSenha.clearFocus()

                val usuario = Usuario(
                    email = edtEmail.text.toString(),
                    senha = edtLoginSenha.text.toString()
                )
                autenticacaoViewModel.logarUsuario(usuario)

            }
        }
    }
}