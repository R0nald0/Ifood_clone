package com.example.ifood_app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ifood_app.R
import com.example.ifood_app.databinding.FragmentHomeBinding
import com.example.ifood_app.domain.model.Categoria
import com.example.ifood_app.domain.model.Loja
import com.example.ifood_app.helper.TipoLayout
import com.example.ifood_app.presentation.ui.adapters.CategoriasAdapter
import com.example.ifood_app.presentation.ui.adapters.UltimasLojasAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapterLojas : UltimasLojasAdapter
    private lateinit var adapterLojasUltimas : UltimasLojasAdapter
    private lateinit var categoriaAdapter : CategoriasAdapter

    private val listCategoria = listOf<Categoria>(
        Categoria("Promoção","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/bagcupones1_eqF1.png?imwidth=128"),
        Categoria("Gourmet","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/gourmet_qo1l.png?imwidth=128"),
        Categoria("Saudável","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/saudaveis_aTKz.png?imwidth=128"),
        Categoria("Brasileiras","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/brasileira_1XfT.png?imwidth=128"),
        Categoria("Prato Feito","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/chamada_2exc.png?imwidth=128"),
        Categoria("Carne","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/carnes_T64X.png?imwidth=128"),
        Categoria("Vegetariana","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/vegetariana_XGvO.png?imwidth=128"),
        Categoria("Arabe","https://static.ifood-static.com.br/image/upload/t_medium/discoveries/arabe_8LSW.png?imwidth=128"),
    )
    private val listaLojas = listOf(
        Loja(
            nome = "Mcdonald's",
            fotoPerfil = "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/854928e6-3ce7-45aa-9558-20b9e548cf31/202211041241_DDGZ_i.jpg?imwidth=96",
            categoria = ""
        ),
        Loja(
            nome = "Habib's",
            fotoPerfil = "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/5f47f639-b17a-42a8-bf0a-13bcff1fc0e9/202103031035_gjeU_i.jpg",
            categoria = "loja 3"
        ),
        Loja(
            nome = "Apache Hamburgueria",
            fotoPerfil = "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/9b81a00a-b4fb-4301-9f76-a0095d0bb72e/201907092006_LKlJ_.jpeg",
            categoria = ""
        )


    )
    val listaSlides = mutableListOf<SlideModel>(
        SlideModel("https://static.ifood-static.com.br/image/upload/t_high/discoveries/1506famososnoifoodprincipal_Qdzl.png?imwidth=1920"),
        SlideModel("https://static.ifood-static.com.br/image/upload/t_high/discoveries/3008diadaesfirraprincipal_FFgW.png?imwidth=1920", ScaleTypes.CENTER_CROP),
        SlideModel("https://static.ifood-static.com.br/image/upload/t_high/discoveries/0201restaurantesqueridinhos4principal_8IE6.png?imwidth=1920", ScaleTypes.CENTER_CROP),
        SlideModel("https://static.ifood-static.com.br/image/upload/t_high/discoveries/CapaPrincipalGenericoPedeiFoodJaRoxo_vUFs.png?imwidth=1920", ScaleTypes.CENTER_CROP)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =FragmentHomeBinding.inflate(inflater,container,false)

        inicialiarUltimasLoja()
        inicializarLojaRc()
        inicializarSlider()
        inicialiarRecyclerVireFiltrosCategorias()
        inicializarFiltrosOrdenacao()
        inicializarAvisoNotificacoes()

        return binding.root
    }

    private fun inicializarAvisoNotificacoes() {
        with(binding) {
            val menuItem = tbHome.menu.findItem(R.id.item_notificacao)
            //TODO verificar erro ao receber valor da notificar
           // val textNotificacao = menuItem.actionView?.findViewById<TextView>(R.id.item_notificacao)
           // textNotificacao?.setText("5")
        }
    }

    private fun inicializarSlider() {

        binding.sliderPromocional.setImageList(listaSlides)

        /*binding.sliderPromocional.setItemClickListener(object: ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                TODO("Not yet implemented")
            }

        })*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicialiarUltimasLoja()
        inicializarLojaRc()
    }

    fun inicialiarUltimasLoja(){
        adapterLojasUltimas =UltimasLojasAdapter(TipoLayout.HORIZONTAL){
            findNavController().navigate(R.id.lojaFragment)
            Toast.makeText(this.context, "clicou", Toast.LENGTH_SHORT).show()
        }
        adapterLojasUltimas.adicionarLista(listaLojas)
        binding.rvUltimasLojas.adapter =adapterLojasUltimas

        binding.rvUltimasLojas.layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false
        )

    }

    fun inicializarLojaRc(){
        adapterLojas =UltimasLojasAdapter(TipoLayout.VERTICAL){
             findNavController().navigate(R.id.lojaFragment)
            Toast.makeText(this.context, "clicou", Toast.LENGTH_SHORT).show()
        }
        adapterLojas.adicionarLista(listaLojas)
        binding.idRcvLojas.adapter =adapterLojas
        binding.idRcvLojas.layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.VERTICAL,false
        )
    }

    fun inicialiarRecyclerVireFiltrosCategorias(){

        categoriaAdapter = CategoriasAdapter()
        categoriaAdapter.adicionarLista(listCategoria)

        binding.rvFiltros.adapter =categoriaAdapter
        binding.rvFiltros.layoutManager = GridLayoutManager(
            context,4
        )

    }

    fun inicializarFiltrosOrdenacao(){

        val checkedPraRetirar = binding.chPraRetirar.isChecked
        val checkedEntregaGratis = binding.chEntregaGratis.isChecked
        binding.chOrdenaccao.setOnClickListener {
            val listOrdenacao = arrayOf("Ordem Padrao","Crescente","Decresente")
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Escolha Uma Ordenaçao")
                .setCancelable(false)
                .setItems(listOrdenacao){_,posicao ->
                  val itemSelecionado = listOrdenacao[posicao]
                    if (posicao == 0 )binding.chOrdenaccao.text = "Ordenação"
                    else binding.chOrdenaccao.text = itemSelecionado
                }.show()
        }
    }


}
