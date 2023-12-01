package com.example.ifood_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ifood_app.databinding.ItemRvAdicionaisBinding
import com.example.ifood_app.domain.model.Adcional

class AdicionaisAdapter: RecyclerView.Adapter<AdicionaisAdapter.AdicionaisViewHolder>() {
    private var listAdiconais= emptyList<Adcional>()

    fun adicionarLista(lista:List<Adcional>){
        listAdiconais = lista
        notifyDataSetChanged()
    }


    inner class AdicionaisViewHolder(val binding : ItemRvAdicionaisBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(categoria :Adcional){
            binding.textAdicionalTirulo.setText(categoria.titulo)
            binding.textDescricao.setText(categoria.descricao)
            binding.textPreco.setText(categoria.preco)
            if (categoria.urlImagem.isNotEmpty()){
                Glide.with(this.itemView).load(categoria.urlImagem).into(binding.imageAdcional)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdicionaisViewHolder {
        val view =ItemRvAdicionaisBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdicionaisViewHolder(view)
    }

    override fun getItemCount()= listAdiconais.size

    override fun onBindViewHolder(holder: AdicionaisViewHolder, position: Int) {
        val adcional  =listAdiconais[position]
        holder.bind(adcional)
    }


}