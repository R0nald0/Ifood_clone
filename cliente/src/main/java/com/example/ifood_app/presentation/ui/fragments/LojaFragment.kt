package com.example.ifood_app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifood_app.R
import com.example.ifood_app.databinding.FragmentLojaBinding
import com.example.ifood_app.domain.model.Produto
import com.example.ifood_app.helper.TipoLayout
import com.example.ifood_app.presentation.ui.adapters.ProdutosAdapter


class LojaFragment : Fragment() {
    private lateinit var binding : FragmentLojaBinding
    private lateinit var produtosAdapter :ProdutosAdapter

    private val listaProddutor = listOf(
        Produto(
            "CHOPP BRAHMA OUTBACK 1L COM 25% DE DESCONTO",
            "O Chopp Brahma Outback com o sabor....",
            "R$ 22,40",
            "R$ 20,40",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/5221af98-5ad4-42e2-a767-23d1545b82d5/202011181213_E09M_.jpeg"
        ),
        Produto(
            "JR RIBS + WINGS JOEY + ICED TEA 500ML",
            "Um combo para você aproveitar...",
            "R$ 104,90",
            "R$ 99,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/185e2a09-94cb-49af-88cb-4b0de2df6dc5/202105101738_140L_.jpeg"
        ),
        Produto(
            "RIBS ON THE BARBIE + 2 ACOMPANHAMENTOS + ICED TEA 1L",
            "Nossa costela suína preparada em chama aberta como manda a tradição australiana, vem com as saborosas cinnamon apples. Inclui 2 acompanhamentos à sua escolha e um Iced Tea de 1l.",
            "R$ 124,90",
            "R$ 99,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/5221af98-5ad4-42e2-a767-23d1545b82d5/201911191742_qCKt_r.jpg"
        ),
        Produto(
            "THE OUTBACKER + COCA-COLA",
            "200g de hambúrguer de carne, queijo, picles, tomate, alface, cebola e maionese. Se preferir, peça com bacon também. ",
            "R$ 49,90",
            "",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/185e2a09-94cb-49af-88cb-4b0de2df6dc5/202303090917_HK7C_i.jpg"
        )

    )




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLojaBinding.inflate(inflater,container,false)
        inicializarToolbar()
        inicializarRecyclerViewProduto()
        inicializarRecyclerViewProdutoDestaque()

        return binding.root
    }

    fun inicializarRecyclerViewProdutoDestaque(){
         produtosAdapter = ProdutosAdapter(TipoLayout.HORIZONTAL){
            findNavController().navigate(R.id.produtosFragment)
            Toast.makeText(this.context, "clicou", Toast.LENGTH_SHORT).show()
        }
        produtosAdapter.adicionarLista(listaProddutor)
        binding.rvProdutosDestaque.adapter =produtosAdapter

        binding.rvProdutosDestaque.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL,false
        )

    }
    fun inicializarRecyclerViewProduto(){
        produtosAdapter = ProdutosAdapter(TipoLayout.VERTICAL){
            findNavController().navigate(R.id.produtosFragment)
            Toast.makeText(this.context, "clicou", Toast.LENGTH_SHORT).show()
        }
        produtosAdapter.adicionarLista(listaProddutor)
        binding.rvProdutos.adapter =produtosAdapter

        binding.rvProdutos.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )

    }
    private fun inicializarToolbar() {

        with(binding){

            btnNavLojaVoltar.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }

            val appCompatActivity = (activity as AppCompatActivity)
            appCompatActivity.setSupportActionBar( collapsedToolbar )
            appCompatActivity.addMenuProvider(
                object : MenuProvider {
                    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                        menuInflater.inflate(R.menu.loja_pesquisa, menu)
                    }

                    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                        return true
                    }

                },
                viewLifecycleOwner
            )

            //Configuração AppBar
            /*appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                println("AppBarChange : $verticalOffset")
                //0 - 357
                val navegacaoVertical = abs(verticalOffset)
                if( navegacaoVertical >= appBarLayout.totalScrollRange ){//Colapsado/fechado
                    textNavLojaTitulo.text = "Outback"
                }else if( navegacaoVertical == 0 ){//Expandido
                    textNavLojaTitulo.text = ""
                }else{//Scrool está acontecendo

                }
            }*/

        }

        //navControler.currentDestination?.label = ""
        //toolbar.setupWithNavController(navControler)
        //NavigationUI.setupWithNavController(toolbar, navControler)

    }
}