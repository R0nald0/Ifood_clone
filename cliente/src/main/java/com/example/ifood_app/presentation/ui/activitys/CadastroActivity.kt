package com.example.ifood_app.presentation.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.core.AlertaMensagem
import com.example.core.esconderTeclado
import com.example.core.exibirMensagem
import com.example.ifood_app.databinding.ActivityCadastroBinding
import com.example.ifood_app.domain.model.Usuario
import com.example.ifood_app.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }
    private val autenticacaoViewModel : AutenticacaoViewModel by viewModels()

   private val alertMessage by lazy {
       AlertaMensagem(this)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inicializar()
    }

    private fun inicializar() {
        iniciarlizarToolbar()
        inicializarListeners()
        inicializarObservables()
    }

    private fun iniciarlizarToolbar() {
       val toolbar  = binding.include2.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title ="Cadastrar Usuário"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun inicializarObservables() {

        autenticacaoViewModel.resultadoValidacao.observe(this){
            with(binding){
                editCadastroNome.error =
                    if (it.nomeInvalid)"preencha o nome" else null
                editCadastroEmail.error =
                    if (it.emailInvalid)"preencha o email" else null
                editCadastroSenha.error=
                    if (it.senhaInvalid)"preencha a senha" else null
                 editCadastroTelefone.error =
                if (it.telefoneInvalid) "preencha o telefone" else null
            }
        }

        autenticacaoViewModel.carregando.observe(this){ carregando ->
            if (carregando){
                alertMessage.call("Cadastrando Usuario...")
            }else{
                alertMessage.hide()
            }
        }

        autenticacaoViewModel.sucesso.observe(this){
            if (it){
                navegarParaLogin()
                exibirMensagem("sucesso ao cadastrasr")
            }else{
                exibirMensagem("erro ao cadastraar")
            }
        }


    }

    private fun navegarParaLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun inicializarListeners() {
        with(binding){

            editCadastroTelefone.clearFocus()
            editCadastroNome.clearFocus()
            editCadastroSenha.clearFocus()
            editCadastroEmail.clearFocus()

            btnCadastrar.setOnClickListener {
                it.esconderTeclado()

                autenticacaoViewModel.cadastroUsuario(Usuario(
                    nome= editCadastroNome.text.toString(),
                    email = editCadastroEmail.text.toString() ,
                    senha =editCadastroSenha.text.toString(),
                    telefone = editCadastroTelefone.text.toString()
                ))
            }
        }
    }

}