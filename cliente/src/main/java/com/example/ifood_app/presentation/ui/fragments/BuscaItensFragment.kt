package com.example.ifood_app.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifood_app.R
import com.example.ifood_app.databinding.FragmentBuscaItensBinding
import com.example.ifood_app.domain.model.Loja
import com.example.ifood_app.presentation.ui.adapters.BuscaItensAdapter

class BuscaItensFragment : Fragment() {
    private lateinit var binding: FragmentBuscaItensBinding
    private lateinit var buscaItensAdapter: BuscaItensAdapter

    private val listaLojas = listOf(
        Loja("Habibs", "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/1b42f128-f698-4713-ad07-c006159fb703/202103031133_xYFj_i.jpg?imwidth=128", "rapido"),
        Loja("Outback", "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/2b157a73-7564-4733-94c1-8d0376e7bb39/202008102155_rNrG_i.png?imwidth=128", "comida"),
        Loja("America", "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/10223314-67e0-40aa-b05d-adf8f21f6427/202004171307_A1jD_i.jpg?imwidth=128", "comida"),
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuscaItensBinding.inflate(
            inflater, container, false
        )

        inicializarRecyclerBuscaItens()

        return binding.root
    }

    private fun inicializarRecyclerBuscaItens() {

        buscaItensAdapter = BuscaItensAdapter()
        buscaItensAdapter.atualizarLista( listaLojas )
        binding.rvBuscaItens.adapter = buscaItensAdapter
        binding.rvBuscaItens.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )

    }
}