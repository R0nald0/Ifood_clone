package com.example.ifood_app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifood_app.R
import com.example.ifood_app.databinding.FragmentProdutosBinding
import com.example.ifood_app.domain.model.Adcional
import com.example.ifood_app.presentation.ui.adapters.AdicionaisAdapter

class ProdutosFragment : Fragment() {
    private lateinit var binding : FragmentProdutosBinding
    private lateinit var adicionaisAdapter : AdicionaisAdapter

    private val adcionalList = listOf(
        Adcional(
           titulo =  "CHOPP BRAHMA OUTBACK 1L COM 25% DE DESCONTO",
            descricao = "O Chopp Brahma Outback com o sabor....",
            "R$ 22,40",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/5221af98-5ad4-42e2-a767-23d1545b82d5/202011181213_E09M_.jpeg"
        ), Adcional(
           titulo =  "CHOPP BRAHMA OUTBACK 1L COM 25% DE DESCONTO",
            descricao = "O Chopp Brahma Outback com o sabor....",
            "R$ 22,40",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/5221af98-5ad4-42e2-a767-23d1545b82d5/201911191742_qCKt_r.jpg"        ),

        Adcional(
            "RIBS ON THE BARBIE + 2 ACOMPANHAMENTOS + ICED TEA 1L",
            "Nossa costela suína preparada em chama aberta como manda a tradição australiana, vem com as saborosas cinnamon apples. Inclui 2 acompanhamentos à sua escolha e um Iced Tea de 1l.",
            "R$ 124,90",

            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/5221af98-5ad4-42e2-a767-23d1545b82d5/201911191742_qCKt_r.jpg"
        ),
        Adcional(
            "THE OUTBACKER + COCA-COLA",
            "200g de hambúrguer de carne, queijo, picles, tomate, alface, cebola e maionese. Se preferir, peça com bacon também. ",
            "R$ 49,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/185e2a09-94cb-49af-88cb-4b0de2df6dc5/202303090917_HK7C_i.jpg"
        )

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutosBinding.inflate(inflater,container,false)
        inicializarRecyclerViewAdicionais()
        inicializarToolbar()
        return binding.root
    }

    private fun inicializarRecyclerViewAdicionais() {
        adicionaisAdapter = AdicionaisAdapter()
        adicionaisAdapter.adicionarLista(adcionalList)
        binding.rvAdicionais.adapter =adicionaisAdapter
        binding.rvAdicionais.layoutManager = LinearLayoutManager(context)
    }

    private fun inicializarToolbar() {

        with(binding){
            btnNavProdutoVoltar.setOnClickListener {
                findNavController().navigate(R.id.lojaFragment)
            }
        }

        //navControler.currentDestination?.label = ""
        //toolbar.setupWithNavController(navControler)
        //NavigationUI.setupWithNavController(toolbar, navControler)

    }



}