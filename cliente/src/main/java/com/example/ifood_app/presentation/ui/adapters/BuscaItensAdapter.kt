package com.example.ifood_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ifood_app.databinding.ItemRvBuscaLojaProdutosBinding
import com.example.ifood_app.domain.model.Loja

class BuscaItensAdapter : RecyclerView.Adapter<BuscaItensAdapter.BuscaItensViewHolder>() {

    private var listaLojas = emptyList<Loja>()
    fun atualizarLista( lista: List<Loja> ){
        listaLojas = lista
        notifyDataSetChanged()
    }
    inner class BuscaItensViewHolder(
        private val binding: ItemRvBuscaLojaProdutosBinding
    ) : RecyclerView.ViewHolder( binding.root ){

        fun bind( loja: Loja ){

            with(binding){
                includeLoja.nomeLoja.text = loja.nome
                if( loja.fotoPerfil.isNotEmpty() ){
                    Glide.with(binding.root.context).load(loja.fotoPerfil ).into( includeLoja.imvLojaFoto)
                }

                //Configurar RecyclerView

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscaItensAdapter.BuscaItensViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemViewBuscaLojaProdutos = ItemRvBuscaLojaProdutosBinding.inflate(
            inflater, parent, false
        )
        return BuscaItensViewHolder( itemViewBuscaLojaProdutos )

    }

    override fun onBindViewHolder(holder: BuscaItensAdapter.BuscaItensViewHolder, position: Int) {
        val loja = listaLojas[position]
        holder.bind( loja )
    }

    override fun getItemCount(): Int {
        return listaLojas.size
    }

}
