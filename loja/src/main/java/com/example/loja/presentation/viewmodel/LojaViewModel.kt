package com.example.loja.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LojaUseCase
import com.example.ifood_app.domain.model.Loja
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LojaViewModel @Inject constructor(
    private val lojaUseCase: LojaUseCase
) :ViewModel(){
    private val _resultadoValidacao = MutableLiveData<Boolean>()
    val validacao: LiveData<Boolean>
        get() = _resultadoValidacao

    private val _sucesso = MutableLiveData<Boolean>()
    val sucesso: LiveData<Boolean>
        get() = _sucesso

    fun cadastroUsuario(loja: Loja){

        val resultado = lojaUseCase.validarDadosLoja(loja)
        _resultadoValidacao.value = resultado

        if (resultado){
            viewModelScope.launch(Dispatchers.IO) {
                val retorno =  lojaUseCase.cadastrarLoja(loja)
                _sucesso.postValue(retorno)
            }

        }
    }
}