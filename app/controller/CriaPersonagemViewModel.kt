package com.olddragon.front.controller

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.olddragon.front.model.*

class CriaPersonagemViewModel : ViewModel() {

    // Estado reativo da tela (StateFlow é ideal para Compose)
    private val _metodoEscolhido = MutableStateFlow(MetodoDistribuicao.CLASSICO)
    val metodoEscolhido: StateFlow<MetodoDistribuicao> = _metodoEscolhido

    private val _valoresAtributos = MutableStateFlow(emptyList<Int>())
    val valoresAtributos: StateFlow<List<Int>> = _valoresAtributos

    private val _atributosFinais = MutableStateFlow(Atributos())
    val atributosFinais: StateFlow<Atributos> = _atributosFinais

    private val _racaSelecionada = MutableStateFlow(Racas.HUMANO)
    val racaSelecionada: StateFlow<Raca> = _racaSelecionada

    private val _classeSelecionada = MutableStateFlow(Classes.GUERREIRO)
    val classeSelecionada: StateFlow<Classe> = _classeSelecionada

    // Funções de interação (lógica)
    fun setMetodo(metodo: MetodoDistribuicao) {
        _metodoEscolhido.value = metodo
        gerarAtributosIniciais() // Gera novos valores ao mudar o método
    }

    fun gerarAtributosIniciais() {
        val valores = gerarValoresSimulados(_metodoEscolhido.value)
        _valoresAtributos.value = valores

        // Para o método Clássico, já alocamos em ordem
        if (_metodoEscolhido.value == MetodoDistribuicao.CLASSICO) {
            _atributosFinais.value = Atributos(
                forca = valores.getOrElse(0) { 0 },
                destreza = valores.getOrElse(1) { 0 },
                constituicao = valores.getOrElse(2) { 0 },
                inteligencia = valores.getOrElse(3) { 0 },
                sabedoria = valores.getOrElse(4) { 0 },
                carisma = valores.getOrElse(5) { 0 }
            )
        } else {
            // Para Aventureiro/Heróico, os valores ficam em _valoresAtributos
            // e o usuário precisará distribuí-los na View. Por enquanto, zera os finais
            _atributosFinais.value = Atributos()
        }
    }

    fun setRaca(raca: Raca) {
        _racaSelecionada.value = raca
    }

    fun setClasse(classe: Classe) {
        _classeSelecionada.value = classe
    }

    // ... (função para distribuir os valores em Aventureiro/Heróico)
}