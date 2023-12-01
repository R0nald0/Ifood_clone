package com.example.ifood_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AutenticaoUseCase
import com.example.ifood_app.domain.model.Usuario
import com.example.ifood_app.domain.usecase.ResultadoAutenticao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
      private val autenticaoUseCase : AutenticaoUseCase
) :ViewModel() {
    private val _carregando = MutableLiveData<Boolean>()
    val carregando : LiveData<Boolean>
        get() = _carregando

    private val _resultadoValidacao  = MutableLiveData<ResultadoAutenticao>()
    val resultadoValidacao  : LiveData<ResultadoAutenticao>
      get()= _resultadoValidacao

    private val _sucesso = MutableLiveData<Boolean>()
    val sucesso :LiveData<Boolean>
    get() =  _sucesso

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged :LiveData<Boolean>
        get() =  _isLogged


    fun  logarUsuario(usuario: Usuario){
        val resultadoAutenticao = autenticaoUseCase.validarLoginUsuario(usuario)
        _resultadoValidacao.value = resultadoAutenticao

              if(resultadoAutenticao.sucessoLogin){
                  viewModelScope.launch {
                      _carregando.value =true
                      val retorno =  autenticaoUseCase.logarUsuario(usuario)
                      _carregando.value =false
                      _sucesso.postValue(retorno)
                  }
              }
    }
    fun cadastroUsuario(usuario: Usuario){

       val resultadoAutenticao = autenticaoUseCase.validarCadastroUsuario(usuario)
       _resultadoValidacao.value = resultadoAutenticao

       if (resultadoAutenticao.sucessoCadastro){
           viewModelScope.launch(Dispatchers.IO) {
               _carregando.value =true
               val retorno =  autenticaoUseCase.cadastrarUsuario(usuario)
               _carregando.value =false
               _sucesso.postValue(retorno)
           }

       }
   }
    fun isLogged(){
        viewModelScope.launch {
            _carregando.value =true
            val results = autenticaoUseCase.isLogged()
            _isLogged.postValue(results)
            _carregando.value =false
        }
    }
}