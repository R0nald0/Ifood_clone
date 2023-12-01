package com.example.ifood_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ifood_app.databinding.ItemRvLojasBinding
import com.example.ifood_app.databinding.ItemRvUltimasLojasBinding
import com.example.ifood_app.domain.model.Loja
import com.example.ifood_app.helper.TipoLayout

class UltimasLojasAdapter(
    private val tipoLayout :TipoLayout,
    private val onClick: () ->Unit
) : RecyclerView.Adapter<ViewHolder>() {

    private var listLojas= emptyList<Loja>()

    fun adicionarLista(lista:List<Loja>){
        listLojas = lista
        notifyDataSetChanged()
    }


      inner class UltimasLojasViewHolder(ultimasLojasItemLayout : ItemRvUltimasLojasBinding):ViewHolder(ultimasLojasItemLayout.root){
         val  binding : ItemRvUltimasLojasBinding
      init {
          binding = ultimasLojasItemLayout
      }
      fun bind(loja :Loja, onnClick: () -> Unit){
          binding.tvXNomeLoja.setText(loja.nome)
          if (loja.fotoPerfil.isNotEmpty()){
              Glide.with(this.itemView).load(loja.fotoPerfil).into(binding.imvLoja)
          }
          binding.clLojaUltima.setOnClickListener {
              onnClick()
          }

      }
  }

      inner class LojasViewHolder(lojaitemLayout : ItemRvLojasBinding):ViewHolder(lojaitemLayout.root){
          val  binding :ItemRvLojasBinding
          init {
              binding = lojaitemLayout
          }

      fun bind(loja :Loja, onnClick: () -> Unit){
          binding.nomeLoja.setText(loja.nome,)
          if (loja.fotoPerfil.isNotEmpty()){
              Glide.with(this.itemView).load(loja.fotoPerfil).into(binding.imvLojaFoto)
          }
          binding.clLoja.setOnClickListener {
             onnClick()
          }

      }
  }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (tipoLayout == TipoLayout.HORIZONTAL){
            val view = ItemRvUltimasLojasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return UltimasLojasViewHolder(view)
        }

        val view = ItemRvLojasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LojasViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listLojas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loja = listLojas[position]
        when(holder){
            is UltimasLojasViewHolder ->{
                holder.bind(loja,onClick)
            }
            is LojasViewHolder->{
                holder.bind(loja,onClick)
            }

        }

    }

}