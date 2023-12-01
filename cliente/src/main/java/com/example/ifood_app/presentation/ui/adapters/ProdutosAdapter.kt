package com.example.ifood_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ifood_app.databinding.ItemRvProdutosBinding
import com.example.ifood_app.databinding.ItemRvProutosDestaqueBinding
import com.example.ifood_app.domain.model.Produto
import com.example.ifood_app.helper.TipoLayout

class ProdutosAdapter(
    private val tipoLayout : TipoLayout,
    private val onClick: () ->Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listProduto= emptyList<Produto>()

    fun adicionarLista(lista:List<Produto>){
        listProduto = lista
       // notifyDataSetChanged()
    }

    inner class ProdutosDestaqueViewHolder(produtosDestaqueItemLayout : ItemRvProutosDestaqueBinding):
        RecyclerView.ViewHolder(produtosDestaqueItemLayout.root){
        val  binding : ItemRvProutosDestaqueBinding
        init {
            binding = produtosDestaqueItemLayout
        }

        fun bind(produto :Produto,onnClick: () -> Unit){
            binding.textTituloDestaque.text = produto.titulo

            if( produto.precoDesconto.isNotEmpty() ){
                binding.textPreco1Destaque.text = produto.precoDesconto
                binding.textPreco2Destaque.text = produto.preco
            }else{
                binding.textPreco1Destaque.text = produto.preco
            }

            if (produto.urlImagem.isNotEmpty()){
                Glide.with(this.itemView).load(produto.urlImagem).into(binding.imageProdutoDestaque)
            }

            binding.clItemProdutoDestaque.setOnClickListener {
                onnClick()
            }

        }
    }

    inner class ProdutosViewHolder(itemProdutoLayout : ItemRvProdutosBinding):
        RecyclerView.ViewHolder(itemProdutoLayout.root){
        val  binding : ItemRvProdutosBinding
        init {
            binding = itemProdutoLayout
        }
        fun bind(produto : Produto, onnClick: () -> Unit){

            binding.textTituloProduto.text = produto.titulo
            binding.textDescricaoProduto.text = produto.descricao
            binding.textPrecoProduto.text = produto.preco

            if (produto.urlImagem.isNotEmpty()){
                Glide.with(this.itemView).load(produto.urlImagem).into(binding.imageProduto)
            }
            binding.clItemProdutoDestaque.setOnClickListener {
                onnClick()
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val produto = listProduto[position]
        when(holder){
            is ProdutosAdapter.ProdutosDestaqueViewHolder ->{
                holder.bind(produto,onClick)
            }
            is ProdutosAdapter.ProdutosViewHolder ->{
                holder.bind(produto,onClick)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (tipoLayout == TipoLayout.HORIZONTAL){
            val view = ItemRvProutosDestaqueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ProdutosDestaqueViewHolder(view)
        }

        val view = ItemRvProdutosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProdutosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

}