package com.example.loja.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AutenticaoUseCase
import com.example.ifood_app.domain.model.Usuario
import com.example.ifood_app.domain.usecase.ResultadoAutenticao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
    private val authenticateUseCase : AutenticaoUseCase
) :ViewModel() {


    private val _carregando = MutableLiveData<Boolean>()
    val carregando : LiveData<Boolean>
        get() = _carregando

    private val _resultadoValidacao  = MutableLiveData<ResultadoAutenticao>()
    val resultadoValidacao  : LiveData<ResultadoAutenticao>
        get()= _resultadoValidacao

    private val _sucesso = MutableLiveData<Boolean>()
    val sucesso : LiveData<Boolean>
        get() =  _sucesso

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged : LiveData<Boolean>
        get() =  _isLogged


    fun  logarUsuario(usuario: Usuario){
        val resultadoAutenticao = authenticateUseCase.validarLoginUsuario(usuario)
        _resultadoValidacao.value = resultadoAutenticao

        if(resultadoAutenticao.sucessoLogin){
            viewModelScope.launch {
                _carregando.value =true
                val retorno =  authenticateUseCase.logarUsuario(usuario)
                _carregando.value =false
                _sucesso.postValue(retorno)
            }
        }
    }
    fun cadastroUsuario(usuario: Usuario){

        val resultadoAutenticao = authenticateUseCase.validarCadastroUsuario(usuario)
        _resultadoValidacao.value = resultadoAutenticao

        if (resultadoAutenticao.sucessoCadastro){
            viewModelScope.launch {
                _carregando.value =true
                val retorno =  authenticateUseCase.cadastrarUsuario(usuario)
                _carregando.value =false
                _sucesso.postValue(retorno)
            }

        }
    }
    fun isLogged(){
        viewModelScope.launch {
            _carregando.value =true
            val results = authenticateUseCase.isLogged()
            _isLogged.postValue(results)
            _carregando.value =false
        }
    }
}