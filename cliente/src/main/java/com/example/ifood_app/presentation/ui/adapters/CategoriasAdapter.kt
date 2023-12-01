package com.example.ifood_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ifood_app.databinding.ItemRvCategoriasBinding
import com.example.ifood_app.domain.model.Categoria

class CategoriasAdapter: RecyclerView.Adapter<CategoriasAdapter.CategoriaViewHolder>() {
    private var listCategorias= emptyList<Categoria>()

    fun adicionarLista(lista:List<Categoria>){
        listCategorias = lista
        notifyDataSetChanged()
    }


    inner class CategoriaViewHolder(val binding : ItemRvCategoriasBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(categoria :Categoria){
            binding.txvCategoriaName.setText(categoria.nome)
            if (categoria.urlImagem.isNotEmpty()){
                Glide.with(this.itemView).load(categoria.urlImagem).into(binding.imgCategoria)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = ItemRvCategoriasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriaViewHolder(view)
    }

    override fun getItemCount()= listCategorias.size

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria  =listCategorias[position]
        holder.bind(categoria)
    }


}