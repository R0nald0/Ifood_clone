package com.example.loja.presentation.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.example.loja.R
import com.example.loja.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        iniciarlizarToolbar()
        inicializarMenuPrincipal()

       // inicializarListeners()
       //  inicializarObservables()
    }



    private fun iniciarlizarToolbar() {
        val toolbar  = binding.includeToolbar.toolbarLoja
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title ="Gerenciamneto da Loja"
        }
    }
    private fun inicializarMenuPrincipal() {
       addMenuProvider(object :MenuProvider{
           override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
               menuInflater.inflate(R.menu.menu_principal_loja,menu)
           }

           override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
               when(menuItem.itemId){
                 R.id.id_tempo ->{
                     startActivity(Intent(applicationContext,TaxaTempoActivity::class.java))
                 }
                   R.id.menu_cardapio ->{
                       startActivity(Intent(applicationContext,CardapioActivity::class.java))
                   }


                   R.id.id_dados_loja ->{
                     startActivity(Intent(applicationContext,DadoLojalActivity::class.java))
                 }
                 R.id.id_dados_loja ->{}
                 R.id.id_sair ->{
                     FirebaseAuth.getInstance().signOut()
                     finish()
                     startActivity(Intent(applicationContext, LoginActivity::class.java))
                 }

               }
               return true
           }

       })

    }


}