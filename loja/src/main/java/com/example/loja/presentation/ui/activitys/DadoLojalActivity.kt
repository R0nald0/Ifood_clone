package com.example.loja.presentation.ui.activitys

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.core.esconderTeclado
import com.example.core.exibirMensagem
import com.example.ifood_app.domain.model.Loja
import com.example.loja.databinding.ActivityDadosLojaBinding
import com.example.loja.presentation.viewmodel.LojaViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DadoLojalActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDadosLojaBinding.inflate(layoutInflater)
    }

    private val lojaViewModel by viewModels<LojaViewModel>()
    private var uriImagemSelecionada: Uri? = null
    private var temPermissaoGaleria = false

    private lateinit var gerenciadorPermissoes  : ActivityResultLauncher <Array<String>>

    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        if(uri != null){
            binding.imagePerfilLoja.setImageURI( uri )
            uriImagemSelecionada = uri
        }else{
            exibirMensagem("Nenhuma imagem selecionada")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()

    }

    private fun inicializar() {
        //solicitarPermissoes()
        inicializarListeners()
        inicializarObservables()
        inicializarToolbar()
        inicializarSpinner()
        inicializarConfiguracoesGaleria()
    }

    private fun inicializarObservables() {
         lojaViewModel.validacao.observe(this){rValidacao->
              if (!rValidacao){
                  exibirMensagem("Preencha todos os campos para prosseguir")
              }
         }
        lojaViewModel.sucesso.observe(this){sucessocadastro ->
            if (sucessocadastro){
                navegarParaTelaInicial()
                exibirMensagem("Sucesso ao cadastrar Loja")
            }else{
                exibirMensagem("Erro ao cadastrar Loja")
             }

        }
    }
    fun navegarParaTelaInicial(){
        startActivity(
            Intent(this, MainActivity::class.java)
        )
        finish()
    }

    private fun inicializarSpinner() {

        val categorias = listOf(
            "Selecione uma categoria",
            "Lanches", "Pizzas", "Japonesa", "Brasileira"
        )

        binding.spinnerCategorias.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categorias
        )

    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeToolbar.toolbarLoja
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Dados da loja"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun solicitarPermissoes() {

        //Verificar permissões que o usuário já tem
        val permissoesNegadas = mutableListOf<String>()


        var temPermissaoGaleria = ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (!temPermissaoGaleria)
            permissoesNegadas.add(Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permissoesNegadas.isNotEmpty()) {

            gerenciadorPermissoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ){ permissoes: Map<String, Boolean> ->

                temPermissaoGaleria = permissoes[Manifest.permission.READ_EXTERNAL_STORAGE]
                    ?: temPermissaoGaleria

            }
            gerenciadorPermissoes.launch( permissoesNegadas.toTypedArray() )

        }
    }

    private fun inicializarConfiguracoesGaleria() {
        binding.btnSelecionarImagemPerfil.setOnClickListener {
            if( temPermissaoGaleria ){
                abrirGaleria.launch("image/*")//Mime Type
            }else{
                Toast.makeText(this, "Você não tem permissão de galeria", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicializarListeners() {
        with(binding){

            edtTelefone.clearFocus()
            edtNomeLoja.clearFocus()
            edtCnpj.clearFocus()
            edtEspecialidade.clearFocus()
            edtRazaoSocial.clearFocus()

            btnCadastrar.setOnClickListener {
                it.esconderTeclado()

                lojaViewModel.cadastroUsuario(
                   Loja(
                       nome = edtNomeLoja.text.toString(),
                       razaoSocial = edtRazaoSocial.text.toString(),
                       cnpj = edtCnpj.text.toString(),
                       categoria = 1,
                       especialidade = edtEspecialidade.text.toString(),
                       imageCapa = "",
                       fotoPerfil = "",
                       telefone = edtTelefone.text.toString(),
                       email = ""
                       )
                )
            }
        }
    }
}